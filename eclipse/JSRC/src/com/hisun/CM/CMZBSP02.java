package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMR;
import com.hisun.CI.CICCUST;
import com.hisun.DC.DCCUCINF;
import com.hisun.DC.DCRCDORD;
import com.hisun.DC.DCRPRDPR;
import com.hisun.SC.SCCBATH;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CMZBSP02 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM027";
    String WS_ERR_MSG = " ";
    String WS_CARD_NO_I = " ";
    String WS_BV_CODE_I = " ";
    String WS_BV_NO_I = " ";
    int WS_I = 0;
    char WS_OPEN_CARD_FLG = ' ';
    short WS_FILE_LEN = 0;
    CMZBSP02_WS_BATH_PARM WS_BATH_PARM = new CMZBSP02_WS_BATH_PARM();
    char WS_END_FLG = ' ';
    CMZBSP02_WS_OUTPUT WS_OUTPUT = new CMZBSP02_WS_OUTPUT();
    String WS_TS = " ";
    int WS_CNT_ORD = 0;
    int WS_CNT_BIN = 0;
    int WS_CNT_BIN2 = 0;
    long WS_AP_SEQ = 0;
    int WS_CNT_T = 0;
    CMCMSG_ERROR_MSG CMCMSG_ERROR_MSG = new CMCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDORD DCRCDORD = new DCRCDORD();
    CMRBSPM CMRBSPM = new CMRBSPM();
    CMRBSP15 CMRBSP15 = new CMRBSP15();
    CICOPDCP CICOPDCP = new CICOPDCP();
    DCCSSPEC DCCSSPEC = new DCCSSPEC();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    CMCBSPIN CMCBSPIN = new CMCBSPIN();
    CMCSPEC1 CMCSPEC1 = new CMCSPEC1();
    DCCUOPEN DCCUOPEN = new DCCUOPEN();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICCUST CICCUST = new CICCUST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCBATH SCCBATH;
    CMCSPEC2 CMCSPEC2;
    public void MP(SCCGWA SCCGWA, CMCSPEC2 CMCSPEC2) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCSPEC2 = CMCSPEC2;
        CEP.TRC(SCCGWA);
        JIBS_sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
        JIBS_date = new Date();
        WS_TS = JIBS_sdf.format(JIBS_date);
        CEP.TRC(SCCGWA, "BSPIPENCARDSTART");
        CEP.TRC(SCCGWA, "START-TIME=");
        CEP.TRC(SCCGWA, WS_TS);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BSPOPENCARDEND");
        CEP.TRC(SCCGWA, "CMZBSP02 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "111");
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        CEP.TRC(SCCGWA, "222");
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        CEP.TRC(SCCGWA, "333");
        SCCBATH = (SCCBATH) SCCGWA.COMM_AREA.BAT_AREA_PTR;
        CEP.TRC(SCCGWA, SCCBATH.PARM);
        WS_BATH_PARM.WS_PARM_BUSITYPE = CMCSPEC2.BUSE_TYPE;
        WS_BATH_PARM.WS_PARM_BATNO = CMCSPEC2.BAT_NO;
        WS_FILE_LEN = 1923;
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_PARM_BUSITYPE);
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_PARM_BATNO);
        CEP.TRC(SCCGWA, CMCSPEC2);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCSPEC2);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CMCSPEC1);
        CEP.TRC(SCCGWA, "555");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B050_CARD_INFOMATION_OPEN();
        if (pgmRtn) return;
        B070_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, CMCSPEC1.TR_TYPE);
        CEP.TRC(SCCGWA, CMCSPEC1.REQ_SYS_JRN);
        CEP.TRC(SCCGWA, CMCSPEC1.REQ_DATE);
        CEP.TRC(SCCGWA, CMCSPEC1.REQ_SYS);
        CEP.TRC(SCCGWA, CMCSPEC1.REQ_CHNL);
        CEP.TRC(SCCGWA, CMCSPEC1.TL_NO);
        CEP.TRC(SCCGWA, CMCSPEC1.TR_BR);
        CEP.TRC(SCCGWA, CMCSPEC1.AP_REF);
        CEP.TRC(SCCGWA, CMCSPEC1.CHN_BUS_TYP);
        CEP.TRC(SCCGWA, CMCSPEC1.CARD_PROD_CD);
        CEP.TRC(SCCGWA, CMCSPEC1.CARD_BIN);
        CEP.TRC(SCCGWA, CMCSPEC1.CARD_SEG1);
        CEP.TRC(SCCGWA, CMCSPEC1.CARD_SEQ);
        CEP.TRC(SCCGWA, CMCSPEC1.PRIM_CD_NO);
        CEP.TRC(SCCGWA, CMCSPEC1.PRIM_CD_HD_IDTYP);
        CEP.TRC(SCCGWA, CMCSPEC1.PRIM_CD_HD_IDNO);
        CEP.TRC(SCCGWA, CMCSPEC1.PRIM_CD_PSW);
        CEP.TRC(SCCGWA, CMCSPEC1.HOLDER_IDTYP);
        CEP.TRC(SCCGWA, CMCSPEC1.HOLDER_IDNO);
        CEP.TRC(SCCGWA, CMCSPEC1.HOLDER_CINO);
        CEP.TRC(SCCGWA, CMCSPEC1.HOLDER_NAME);
        CEP.TRC(SCCGWA, CMCSPEC1.HOLDER_TEL_NUM);
        CEP.TRC(SCCGWA, CMCSPEC1.HOLDER_POST_CODE);
        CEP.TRC(SCCGWA, CMCSPEC1.HOLDER_ADDR);
        CEP.TRC(SCCGWA, CMCSPEC1.HOLDER_EMAIL);
        CEP.TRC(SCCGWA, CMCSPEC1.EMBOSS_NAME);
        CEP.TRC(SCCGWA, CMCSPEC1.OWNER_IDTYP);
        CEP.TRC(SCCGWA, CMCSPEC1.OWNER_IDNO);
        CEP.TRC(SCCGWA, CMCSPEC1.OWNER_CINO);
        CEP.TRC(SCCGWA, CMCSPEC1.OWNER_NAME);
        CEP.TRC(SCCGWA, CMCSPEC1.AC_NO);
        CEP.TRC(SCCGWA, CMCSPEC1.AC_TYP);
        CEP.TRC(SCCGWA, CMCSPEC1.ISSUE_MTH);
        CEP.TRC(SCCGWA, CMCSPEC1.CARD_AUTO_OPNAC);
        CEP.TRC(SCCGWA, CMCSPEC1.SNAME_TRAN_FLG);
        CEP.TRC(SCCGWA, CMCSPEC1.DNAME_TRAN_FLG);
        CEP.TRC(SCCGWA, CMCSPEC1.OUT_DRAW_FLG);
        CEP.TRC(SCCGWA, CMCSPEC1.CARD_LNK_TYP);
        CEP.TRC(SCCGWA, CMCSPEC1.PROD_LMT_FLG);
        CEP.TRC(SCCGWA, CMCSPEC1.HOLD_AC_FLG);
        CEP.TRC(SCCGWA, CMCSPEC1.AC_TYPE);
        CEP.TRC(SCCGWA, CMCSPEC1.AGENT_FLG);
        CEP.TRC(SCCGWA, CMCSPEC1.AGENT_IDTYP);
        CEP.TRC(SCCGWA, CMCSPEC1.AGENT_IDNO);
        CEP.TRC(SCCGWA, CMCSPEC1.AGENT_NAME);
        CEP.TRC(SCCGWA, CMCSPEC1.CARD_CLS_PROD);
        CEP.TRC(SCCGWA, CMCSPEC1.BV_CD_NO);
        CEP.TRC(SCCGWA, CMCSPEC1.SEQ_GEN_MTH);
        CEP.TRC(SCCGWA, CMCSPEC1.DB_FREE);
        CEP.TRC(SCCGWA, CMCSPEC1.CUS_MGR);
        CEP.TRC(SCCGWA, CMCSPEC1.REG_CENT);
        CEP.TRC(SCCGWA, CMCSPEC1.SUB_BIZ);
        CEP.TRC(SCCGWA, CMCSPEC1.PROC_STS);
        CEP.TRC(SCCGWA, CMCSPEC1.RET_CODE);
        CEP.TRC(SCCGWA, CMCSPEC1.RET_MSG);
        CEP.TRC(SCCGWA, CMCSPEC1.DATE);
        CEP.TRC(SCCGWA, CMCSPEC1.JRNNO);
        CEP.TRC(SCCGWA, CMCSPEC1.VCH_NO);
        CEP.TRC(SCCGWA, CMCSPEC1.CARD_NO);
        CEP.TRC(SCCGWA, CMCSPEC1.INI_PSW);
        CEP.TRC(SCCGWA, CMCSPEC1.BV_CODE);
        CEP.TRC(SCCGWA, CMCSPEC1.BV_NO);
        if (CMCSPEC1.HOLDER_IDTYP.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCSPEC1.HOLDER_IDNO.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCSPEC1.HOLDER_CINO.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCSPEC1.EMBOSS_NAME.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCSPEC1.HOLDER_NAME.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = CMCSPEC1.HOLDER_CINO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (!CICCUST.O_DATA.O_CI_NM.equalsIgnoreCase(CMCSPEC1.HOLDER_NAME)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_NM_ERR);
            }
        }
        if (CMCSPEC1.AC_TYP != '1' 
            && CMCSPEC1.AC_TYP != '2' 
            && CMCSPEC1.AC_TYP != '3') {
            CEP.TRC(SCCGWA, CMCSPEC1.AC_TYP);
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
        if (CMCSPEC1.ISSUE_MTH != '0' 
            && CMCSPEC1.ISSUE_MTH != '1') {
            CEP.TRC(SCCGWA, CMCSPEC1.ISSUE_MTH);
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
        if (CMCSPEC1.AGENT_FLG != '0' 
            && CMCSPEC1.AGENT_FLG != '1') {
            CEP.TRC(SCCGWA, CMCSPEC1.AGENT_FLG);
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
        if (CMCSPEC1.AC_TYPE != '1' 
            && CMCSPEC1.AC_TYPE != '2' 
            && CMCSPEC1.AC_TYPE != '3' 
            && CMCSPEC1.AC_TYPE != '4') {
            CEP.TRC(SCCGWA, CMCSPEC1.AC_TYPE);
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
    }
    public void B030_GET_CARDNO() throws IOException,SQLException,Exception {
        if (CMCBSPIN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCBSPIN.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
        for (WS_I = 1; WS_I <= 5 
            && CMCBSPIN.INPUT[WS_I-1].STA_BV_NO_18.trim().length() != 0 
            && WS_END_FLG != 'E'; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, CMCBSPIN.INPUT[WS_I-1].STA_BV_NO_18);
            CEP.TRC(SCCGWA, CMCBSPIN.INPUT[WS_I-1].REDEFINES7.CARD_NO_18_STA);
            CEP.TRC(SCCGWA, CMCBSPIN.INPUT[WS_I-1].END_BV_NO_18);
            CEP.TRC(SCCGWA, CMCBSPIN.INPUT[WS_I-1].REDEFINES10.CARD_NO_18_END);
            CEP.TRC(SCCGWA, CMCBSPIN.INPUT[WS_I-1].NUM);
            CEP.TRC(SCCGWA, CMCBSPIN.INPUT[WS_I-1].NUM_USE);
            if (CMCBSPIN.INPUT[WS_I-1].NUM > CMCBSPIN.INPUT[WS_I-1].NUM_USE 
                && CMCBSPIN.INPUT[WS_I-1].STA_BV_NO_18.compareTo(CMCBSPIN.INPUT[WS_I-1].END_BV_NO_18) <= 0) {
                IBS.init(SCCGWA, DCCFCDGG);
                DCCFCDGG.VAL.FUNC = 'G';
                CEP.TRC(SCCGWA, CMCBSPIN.INPUT[WS_I-1].STA_BV_NO_18);
                if (DCCFCDGG.VAL.CARD_NO == null) DCCFCDGG.VAL.CARD_NO = "";
                JIBS_tmp_int = DCCFCDGG.VAL.CARD_NO.length();
                for (int i=0;i<19-JIBS_tmp_int;i++) DCCFCDGG.VAL.CARD_NO += " ";
                if (CMCBSPIN.INPUT[WS_I-1].STA_BV_NO_18 == null) CMCBSPIN.INPUT[WS_I-1].STA_BV_NO_18 = "";
                JIBS_tmp_int = CMCBSPIN.INPUT[WS_I-1].STA_BV_NO_18.length();
                for (int i=0;i<18-JIBS_tmp_int;i++) CMCBSPIN.INPUT[WS_I-1].STA_BV_NO_18 += " ";
                DCCFCDGG.VAL.CARD_NO = CMCBSPIN.INPUT[WS_I-1].STA_BV_NO_18 + DCCFCDGG.VAL.CARD_NO.substring(18);
                WS_BV_NO_I = CMCBSPIN.INPUT[WS_I-1].STA_BV_NO_18;
                S000_CALL_DCZFCDGG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, DCCFCDGG.VAL.CARD_NO_GEN);
                WS_CARD_NO_I = DCCFCDGG.VAL.CARD_NO_GEN;
                WS_END_FLG = 'E';
            }
        }
        CEP.TRC(SCCGWA, "WS-CARD-NO-I");
        CEP.TRC(SCCGWA, WS_CARD_NO_I);
        CEP.TRC(SCCGWA, WS_CARD_NO_I);
    }
    public void B050_CARD_INFOMATION_OPEN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRBSPM);
        IBS.init(SCCGWA, CMCBSPIN);
        CMRBSPM.KEY.AP_TYPE = WS_BATH_PARM.WS_PARM_BUSITYPE;
        CMRBSPM.KEY.AP_BATNO = WS_BATH_PARM.WS_PARM_BATNO;
        T000_READ_CMTBSPM();
        if (pgmRtn) return;
