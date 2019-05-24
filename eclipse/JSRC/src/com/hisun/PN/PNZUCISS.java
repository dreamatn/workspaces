package com.hisun.PN;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCAOTH;
import com.hisun.BP.BPCFBVQU;
import com.hisun.BP.BPCFFCON;
import com.hisun.BP.BPCFFTXI;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPCOCLWD;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMM;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCQCNGL;
import com.hisun.BP.BPCTCALF;
import com.hisun.BP.BPCUABOX;
import com.hisun.BP.BPCUBUSE;
import com.hisun.BP.BPCUCNGM;
import com.hisun.BP.BPRPRMT;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.CI.CICQACRI;
import com.hisun.DC.DCCURHLD;
import com.hisun.DD.DDCUDRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PNZUCISS {
    boolean pgmRtn = false;
    String CPN_F_PNZFQPRD = "PN-F-GET-PRD";
    String CPN_PARM_MT = "BP-PARM-MAINTAIN    ";
    String K_PRDP_TYP = "PRDPR";
    String CPN_U_BPZUABOX = "BP-U-ADD-CBOX";
    String CPN_U_DDZUDRAC = "DD-UNIT-DRAW-PROC";
    String CPN_F_BPZFFTXI = "BP-F-F-TX-INFO";
    String CPN_F_BPZFCALF = "BP-F-F-CAL-FEE";
    String CPN_U_SCZHMPW = "SC-HM-PASSWORD";
    String CPN_U_BPZPQORG = "BP-P-INQ-ORG";
    String CPN_U_PNZUCISS = "PN-U-ISS-PNT";
    String CPN_U_BPZPFHIS = "BP-PROC-FHIS";
    String CPN_U_BPZUBUSE = "BP-U-TLR-BV-USE";
    String CPN_U_BPZUCNGM = "BP-U-MAINT-CNGM";
    String CPN_U_BPZQCNGL = "BP-P-INQ-CNGL";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String CPN_U_DCZURHLD = "DC-UNIT-RHLD";
    String CPN_U_CIACCU = "CI-INQ-ACCU";
    String CPN_U_BPZFBVQU = "BP-F-BV-PRM-QUERY";
    String K_MMO = "ISS";
    String K_TBL_BCC = "PNTPROD";
    String K_TBL_FPSW = "PNTDFPSW";
    String K_CNTR_TYPE = "CACH";
    String K_EVENT_CODE = "CR      ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_PROD_CO1 = "6406060000";
    String K_PROD_CO2 = "6406060000";
    String K_BVCD_BP1 = "C00001";
    String K_OUTPUT_FMT1 = "PN009";
    char WS_TABLE_FLG = ' ';
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    PNZUCISS_WS_MSGID WS_MSGID = new PNZUCISS_WS_MSGID();
    PNZUCISS_CP_PROD_CD CP_PROD_CD = new PNZUCISS_CP_PROD_CD();
    PNZUCISS_WS_MAC_DA WS_MAC_DA = new PNZUCISS_WS_MAC_DA();
    PNZUCISS_WS_DATE_ALL WS_DATE_ALL = new PNZUCISS_WS_DATE_ALL();
    int WS_DATE = 0;
    short WS_HEAD_NO = 0;
    short WS_NO = 0;
    String WS_CC_NO_CHANGE = " ";
    short WS_CNT_NUM = 0;
    char WS_CI_TYP = ' ';
    char WS_PAY_MTH_FLG = ' ';
    char WS_APB_FLG = ' ';
    char WS_STS_CHANGE_FLG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    PNCMSG_ERROR_MSG PNCMSG_ERROR_MSG = new PNCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCUABOX BPCUABOX = new BPCUABOX();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    PNCFQPRD PNCFQPRD = new PNCFQPRD();
    BPCFFTXI BPCFFTXI = new BPCFFTXI();
    BPCTCALF BPCTCALF = new BPCTCALF();
    SCCHMPW SCCHMPW = new SCCHMPW();
    DCCURHLD DCCURHLD = new DCCURHLD();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    BPCFFCON BPCFFCON = new BPCFFCON();
    BPCQCNGL BPCQCNGL = new BPCQCNGL();
    BPCUCNGM BPCUCNGM = new BPCUCNGM();
    BPCAOTH BPCAOTH = new BPCAOTH();
    CICACCU CICACCU = new CICACCU();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    PNVPPN01 PNVPPN01 = new PNVPPN01();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    CICQACRI CICQACRI = new CICQACRI();
    CICCUST CICCUST = new CICCUST();
    PNVMPRD PNVMPRD = new PNVMPRD();
    AICUUPIA AICUUPIA = new AICUUPIA();
    PNCOTCEL PNCOTCEL = new PNCOTCEL();
    PNRBCC PNRBCC = new PNRBCC();
    PNRDFPSW PNRDFPSW = new PNRDFPSW();
    PNRPROD PNRPROD = new PNRPROD();
    int WK_DATE = 0;
    SCCGWA SCCGWA;
    BPCPORUP_DATA_INFO BPCPORUP;
    PNCUCISS PNCUCISS;
    public void MP(SCCGWA SCCGWA, PNCUCISS PNCUCISS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PNCUCISS = PNCUCISS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PNZUCISS return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.SUP1_ID);
        B100_INPUT_CHK_PROC();
        if (pgmRtn) return;
        B101_PRD_CHK_PROC();
        if (pgmRtn) return;
        B201_BV_OUT_PROC();
        if (pgmRtn) return;
        B203_COST_FEE_PROC();
        if (pgmRtn) return;
        B204_DRCR_AC_PROC();
        if (pgmRtn) return;
        B301_INQ_GL_MASTER();
        if (pgmRtn) return;
        B302_WRITE_GL_MASTER_PROC();
        if (pgmRtn) return;
        B205_PNT_EVT_PROC();
        if (pgmRtn) return;
        B211_BCC_MST_PROC();
        if (pgmRtn) return;
    }
    public void B100_INPUT_CHK_PROC() throws IOException,SQLException,Exception {
        if (PNCUCISS.APP_TYPE == '0') {
            WS_APB_FLG = '1';
            if (!PNCUCISS.PRD_CD.equalsIgnoreCase(K_PROD_CO1)) {
                WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_PROD_MUST_P;
                WS_ERR_INFO = "UCISS-APP-TYPE=" + PNCUCISS.APP_TYPE + ",UCISS-PRD-CD=" + PNCUCISS.PRD_CD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            WS_APB_FLG = '2';
            if (!PNCUCISS.PRD_CD.equalsIgnoreCase(K_PROD_CO2)) {
                WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_PROD_MUST_C;
                WS_ERR_INFO = "UCISS-APP-TYPE=" + PNCUCISS.APP_TYPE + ",UCISS-PRD-CD=" + PNCUCISS.PRD_CD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        if (PNCUCISS.PAY_TYPE == 'T') {
            if (PNCUCISS.APP_AC.trim().length() == 0) {
                WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_APAC_MUST_IPT;
                WS_ERR_INFO = "UCISS-APP-AC=" + PNCUCISS.APP_AC;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = PNCUCISS.APP_AC;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_CI_TYP);
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC") 
                && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
                && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                CEP.TRC(SCCGWA, "ERR AC-TYPE");
                WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_AC_TYP_NOT_ALLOW;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, PNCUCISS.APP_TYPE);
            if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                if (WS_APB_FLG == '1') {
                    if (CICQACRI.O_DATA.O_CI_TYP != '1') {
                        WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_CUSTY_NOT_COMM;
                        WS_ERR_INFO = "UCISS-APP-AC=" + PNCUCISS.APP_AC;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                } else {
                    if (CICQACRI.O_DATA.O_CI_TYP == '1') {
                        WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_CUSTY_NOT_COMM;
                        WS_ERR_INFO = "UCISS-APP-AC=" + PNCUCISS.APP_AC;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (PNCUCISS.C_T_FLG != 'T') {
                        WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_CTBT_NOT_MATCH;
                        WS_ERR_INFO = "UCISS-C-T-FLG=" + PNCUCISS.C_T_FLG + ",UCISS-PAY-TYPE=" + PNCUCISS.PAY_TYPE;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                    if (PNCUCISS.APB_NO.trim().length() == 0) {
                        WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_APBNO_MUST_INPT;
                        WS_ERR_INFO = "UCISS-APP-TYPE=" + PNCUCISS.APP_TYPE + ",UCISS-APB-NO=" + PNCUCISS.APB_NO;
                        S000_ERR_MSG_PROC();
                        if (pgmRtn) return;
                    }
                }
            } else {
                if (PNCUCISS.CREV_NO.trim().length() == 0) {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_CREV_NO_M_IPT);
                }
                if (PNCUCISS.FEE_FLG == '1') {
                    CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_FEE_FLG_N_TRN);
                }
            }
        }
        if (PNCUCISS.C_T_FLG == 'C') {
            if (PNCUCISS.TRN_FLG != '1') {
                WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_CASH_NOT_TRN;
                WS_ERR_INFO = "UCISS-C-T-FLG=" + PNCUCISS.C_T_FLG + ",UCISS-TRN-FLG=" + PNCUCISS.TRN_FLG;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (PNCUCISS.APP_TYPE != '0') {
                CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_APBTYP_ERR);
            }
        }
        if ((PNCUCISS.APP_TYPE == '0' 
            && PNCUCISS.BV_TYP.equalsIgnoreCase("C00006")) 
            || (PNCUCISS.APP_TYPE == '1' 
            && PNCUCISS.BV_TYP.equalsIgnoreCase("198"))) {
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_BV_APP_TYP_NO_MATCH);
        }
        if (SCCGWA.COMM_AREA.CANCEL_IND != 'Y') {
            IBS.init(SCCGWA, PNRBCC);
            PNRBCC.KEY.BILL_NO = PNCUCISS.CC_NO;
            T000_READ_PNTBCC();
            if (pgmRtn) return;
            if (WS_TABLE_FLG == 'Y') {
                WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_BCC_REC_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B101_PRD_CHK_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = PNCUCISS.PRD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, 1);
        if (BPCPQPRD.PARM_CODE.trim().length() == 0) {
            CEP.TRC(SCCGWA, "---------JICHU CHANPIN SPACE---------");
            CEP.ERR(SCCGWA, PNCMSG_ERROR_MSG.PN_PRD_PARM_NUL);
        }
        CEP.TRC(SCCGWA, "--------GET----JICHUCHANPIN---------");
        CEP.TRC(SCCGWA, BPCPQPRD.PARM_CODE);
        IBS.init(SCCGWA, PNRPROD);
        PNRPROD.KEY.IBS_AC_BK = SCCGWA.COMM_AREA.TR_BANK;
        if (PNRPROD.KEY.CODE == null) PNRPROD.KEY.CODE = "";
        JIBS_tmp_int = PNRPROD.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) PNRPROD.KEY.CODE += " ";
        PNRPROD.KEY.CODE = "999999" + PNRPROD.KEY.CODE.substring(6);
        if (PNRPROD.KEY.CODE == null) PNRPROD.KEY.CODE = "";
        JIBS_tmp_int = PNRPROD.KEY.CODE.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) PNRPROD.KEY.CODE += " ";
        if (BPCPQPRD.PARM_CODE == null) BPCPQPRD.PARM_CODE = "";
        JIBS_tmp_int = BPCPQPRD.PARM_CODE.length();
        for (int i=0;i<10-JIBS_tmp_int;i++) BPCPQPRD.PARM_CODE += " ";
        PNRPROD.KEY.CODE = PNRPROD.KEY.CODE.substring(0, 7 - 1) + BPCPQPRD.PARM_CODE + PNRPROD.KEY.CODE.substring(7 + 10 - 1);
        PNTPROD_RD = new DBParm();
        PNTPROD_RD.TableName = "PNTPROD";
        PNTPROD_RD.fst = true;
        PNTPROD_RD.order = "EFF_DATE DESC";
        IBS.READ(SCCGWA, PNRPROD, PNTPROD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_FLG = 'N';
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_PNTPROD_DATE_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_TBL_BCC;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PNRPROD.CCY);
        CEP.TRC(SCCGWA, PNRPROD.PAY_TERM);
        CEP.TRC(SCCGWA, PNRPROD.AUTO_REL);
    }
    public void B201_BV_OUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = K_BVCD_BP1;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        WS_HEAD_NO = BPCFBVQU.TX_DATA.HEAD_LENGTH;
        WS_NO = BPCFBVQU.TX_DATA.NO_LENGTH;
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.HEAD_LENGTH);
        CEP.TRC(SCCGWA, BPCFBVQU.TX_DATA.NO_LENGTH);
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.BV_CODE = K_BVCD_BP1;
        if (WS_HEAD_NO != 0) {
            if (PNCUCISS.CC_NO == null) PNCUCISS.CC_NO = "";
            JIBS_tmp_int = PNCUCISS.CC_NO.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) PNCUCISS.CC_NO += " ";
            BPCUBUSE.HEAD_NO = PNCUCISS.CC_NO.substring(0, WS_HEAD_NO);
        }
        WS_HEAD_NO = (short) (WS_HEAD_NO + 1);
        if (PNCUCISS.CC_NO == null) PNCUCISS.CC_NO = "";
        JIBS_tmp_int = PNCUCISS.CC_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUCISS.CC_NO += " ";
        BPCUBUSE.BEG_NO = PNCUCISS.CC_NO.substring(WS_HEAD_NO - 1, WS_HEAD_NO + WS_NO - 1);
        if (PNCUCISS.CC_NO == null) PNCUCISS.CC_NO = "";
        JIBS_tmp_int = PNCUCISS.CC_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUCISS.CC_NO += " ";
        BPCUBUSE.END_NO = PNCUCISS.CC_NO.substring(WS_HEAD_NO - 1, WS_HEAD_NO + WS_NO - 1);
        BPCUBUSE.NUM = 1;
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void B204_DRCR_AC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, PNCUCISS.PAY_TYPE);
        if (PNCUCISS.PAY_TYPE == 'C') {
            B204_01_CALL_CASH_UNT();
            if (pgmRtn) return;
        } else if (PNCUCISS.PAY_TYPE == 'T') {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                B204_02_CALL_AI_UNT();
                if (pgmRtn) return;
            } else {
                B204_03_CALL_DD_UNT();
                if (pgmRtn) return;
            }
        } else {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_PAY_MTH_NOT_EXIST;
            WS_ERR_INFO = "UCISS-PAY-TYPE=" + PNCUCISS.PAY_TYPE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B204_01_CALL_CASH_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUABOX);
        BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUABOX.CCY = PNCUCISS.CCY;
        BPCUABOX.AMT = PNCUCISS.AMT;
        BPCUABOX.OPP_ACNM = PNCUCISS.APP_NM;
        S000_CALL_BPZUABOX();
        if (pgmRtn) return;
    }
    public void B204_02_CALL_AI_UNT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.AC_NO = PNCUCISS.APP_AC;
        AICUUPIA.DATA.CCY = PNCUCISS.CCY;
        AICUUPIA.DATA.AMT = PNCUCISS.AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = PNCUCISS.CREV_NO;
        AICUUPIA.DATA.PAY_MAN = PNCUCISS.APP_NM;
        AICUUPIA.DATA.EVENT_CODE = "DR";
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
        CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
        CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B204_03_CALL_DD_UNT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        IBS.init(SCCGWA, DDCUDRAC);
        if (PNCUCISS.APP_TYPE == '0') {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                DDCUDRAC.CARD_NO = PNCUCISS.APP_AC;
                DDCUDRAC.CHK_PSW_FLG = 'Y';
                CEP.TRC(SCCGWA, PNCUCISS.TRK_DAT2);
                CEP.TRC(SCCGWA, PNCUCISS.TRK_DAT3);
                if (PNCUCISS.PSW.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "CHK001");
                    DDCUDRAC.CHK_PSW = 'P';
                }
                if (PNCUCISS.TRK_DAT2.trim().length() > 0 
                    || PNCUCISS.TRK_DAT3.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "CHK002");
                    DDCUDRAC.CHK_PSW = 'T';
                    if (PNCUCISS.PSW.trim().length() > 0) {
                        CEP.TRC(SCCGWA, "CHK003");
                        DDCUDRAC.CHK_PSW = 'B';
                    }
                }
            } else {
                DDCUDRAC.AC = PNCUCISS.APP_AC;
                DDCUDRAC.CHK_PSW_FLG = 'N';
            }
            DDCUDRAC.PSWD = PNCUCISS.PSW;
            CEP.TRC(SCCGWA, DDCUDRAC.PSWD);
            DDCUDRAC.TRK_DATE2 = PNCUCISS.TRK_DAT2;
            DDCUDRAC.TRK_DATE3 = PNCUCISS.TRK_DAT3;
            DDCUDRAC.CCY = PNCUCISS.CCY;
            DDCUDRAC.CCY_TYPE = '1';
            DDCUDRAC.TX_AMT = PNCUCISS.AMT;
            DDCUDRAC.TX_TYPE = PNCUCISS.PAY_TYPE;
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUDRAC.TX_MMO = "A310";
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        } else {
            DDCUDRAC.PAY_PSWD = PNCUCISS.PAY_PSW;
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
                DDCUDRAC.CARD_NO = PNCUCISS.APP_AC;
                DDCUDRAC.CHK_PSW_FLG = 'Y';
                CEP.TRC(SCCGWA, PNCUCISS.TRK_DAT2);
                CEP.TRC(SCCGWA, PNCUCISS.TRK_DAT3);
                if (PNCUCISS.PSW.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "CHK001");
                    DDCUDRAC.CHK_PSW = 'P';
                }
                if (PNCUCISS.TRK_DAT2.trim().length() > 0 
                    || PNCUCISS.TRK_DAT3.trim().length() > 0) {
                    CEP.TRC(SCCGWA, "CHK002");
                    DDCUDRAC.CHK_PSW = 'T';
                    if (PNCUCISS.PSW.trim().length() > 0) {
                        CEP.TRC(SCCGWA, "CHK003");
                        DDCUDRAC.CHK_PSW = 'B';
                    }
                }
            } else {
                DDCUDRAC.AC = PNCUCISS.APP_AC;
                CEP.TRC(SCCGWA, "DD");
            }
            DDCUDRAC.PSWD = PNCUCISS.PSW;
            CEP.TRC(SCCGWA, DDCUDRAC.PSWD);
            DDCUDRAC.TRK_DATE2 = PNCUCISS.TRK_DAT2;
            DDCUDRAC.TRK_DATE3 = PNCUCISS.TRK_DAT3;
            DDCUDRAC.CCY = PNCUCISS.CCY;
            DDCUDRAC.CCY_TYPE = '1';
            DDCUDRAC.TX_AMT = PNCUCISS.AMT;
            DDCUDRAC.TX_TYPE = PNCUCISS.PAY_TYPE;
            DDCUDRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCUDRAC.TX_MMO = "A310";
            CEP.TRC(SCCGWA, PNCUCISS.BV_TYP);
            if (PNCUCISS.BV_TYP.equalsIgnoreCase("C00006")) {
                CEP.TRC(SCCGWA, "ZHI QU 11111");
                DDCUDRAC.CHQ_TYPE = '4';
                DDCUDRAC.CHQ_NO = PNCUCISS.APB_NO;
                DDCUDRAC.CHQ_ISS_DATE = PNCUCISS.APB_VLDT;
            }
            CEP.TRC(SCCGWA, "BIG FAIL");
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DDCUDRAC.CARD_NO);
    }
    public void B211_BCC_MST_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
            B206_BCC_MST_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "--CANCEL OUT--");
            B210_OUTPUT_PROC();
            if (pgmRtn) return;
        } else {
            B208_GET_DUEDATE_PROC();
            if (pgmRtn) return;
            B207_BCC_MST_PROC();
            if (pgmRtn) return;
            B209_WRT_PNTDFPSW_PROC();
            if (pgmRtn) return;
        }
    }
    public void B220_BP_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.AC_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.AC = PNCUCISS.CC_NO;
        BPCPFHIS.DATA.ACO_AC = PNCUCISS.CC_NO;
        BPCPFHIS.DATA.TX_TOOL = PNCUCISS.CC_NO;
        BPCPFHIS.DATA.REF_NO = PNCUCISS.CC_NO;
        BPCPFHIS.DATA.SUMUP_FLG = '5';
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.BV_CODE = K_BVCD_BP1;
        if (PNCUCISS.CC_NO == null) PNCUCISS.CC_NO = "";
        JIBS_tmp_int = PNCUCISS.CC_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUCISS.CC_NO += " ";
        BPCPFHIS.DATA.HEAD_NO = PNCUCISS.CC_NO.substring(0, 8);
        if (PNCUCISS.CC_NO == null) PNCUCISS.CC_NO = "";
        JIBS_tmp_int = PNCUCISS.CC_NO.length();
        for (int i=0;i<30-JIBS_tmp_int;i++) PNCUCISS.CC_NO += " ";
        BPCPFHIS.DATA.BV_NO = PNCUCISS.CC_NO.substring(9 - 1, 9 + 8 - 1);
        BPCPFHIS.DATA.TX_CCY = PNCUCISS.CCY;
        BPCPFHIS.DATA.TX_CCY_TYP = '1';
        BPCPFHIS.DATA.TX_TYPE = PNCUCISS.C_T_FLG;
        BPCPFHIS.DATA.TX_AMT = PNCUCISS.AMT;
        BPCPFHIS.DATA.TX_MMO = "A310";
        BPCPFHIS.DATA.PROD_CD = PNCUCISS.PRD_CD;
        BPCPFHIS.DATA.REMARK = PNCUCISS.USG_RMK;
        BPCPFHIS.DATA.OTH_AC = PNCUCISS.APP_AC;
        CEP.TRC(SCCGWA, PNCUCISS.BCC_CINO);
        BPCPFHIS.DATA.CI_NO = PNCUCISS.BCC_CINO;
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B205_PNT_EVT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PNCUCISS.CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PNCUCISS.AMT;
        BPCPOEWA.DATA.PROD_CODE = PNCUCISS.PRD_CD;
        BPCPOEWA.DATA.AC_NO = PNCUCISS.CC_NO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B207_BCC_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRBCC);
        PNRBCC.KEY.BILL_NO = PNCUCISS.CC_NO;
        PNRBCC.KEY.BILL_KND = PNCUCISS.KND;
        CEP.TRC(SCCGWA, PNCUCISS.CC_NO);
        CEP.TRC(SCCGWA, PNCUCISS.KND);
        PNRBCC.PRD_CD = PNCUCISS.PRD_CD;
        PNRBCC.ISS_DT = SCCGWA.COMM_AREA.AC_DATE;
        PNRBCC.TRN_FLG = PNCUCISS.TRN_FLG;
        PNRBCC.CCY = PNCUCISS.CCY;
        PNRBCC.AMT = PNCUCISS.AMT;
        PNRBCC.STS = '1';
        PNRBCC.PAY_TYPE = PNCUCISS.PAY_TYPE;
        PNRBCC.C_T_FLG = PNCUCISS.C_T_FLG;
        PNRBCC.DUE_DATE = BPCOCLWD.DATE2;
        if (PNCUCISS.APP_TYPE == '0') {
            PNRBCC.APB_TYPE = '1';
        } else {
            PNRBCC.APB_TYPE = '2';
        }
        PNRBCC.APB_NO = PNCUCISS.APB_NO;
        PNRBCC.APB_VALUE_DATE = PNCUCISS.APB_VLDT;
        PNRBCC.APP_AC = PNCUCISS.APP_AC;
        PNRBCC.APP_ACNM = PNCUCISS.APP_NM;
        PNRBCC.PAYEE_AC = PNCUCISS.PAYEE_AC;
        PNRBCC.PAYEE_ACNM = PNCUCISS.PAYEE_NM;
        CEP.TRC(SCCGWA, PNCUCISS.APP_AC);
        CEP.TRC(SCCGWA, PNCUCISS.APP_NM);
        CEP.TRC(SCCGWA, PNCUCISS.PAYEE_AC);
        CEP.TRC(SCCGWA, PNCUCISS.PAYEE_NM);
        CEP.TRC(SCCGWA, "1");
        CEP.TRC(SCCGWA, PNRBCC.APP_AC);
        CEP.TRC(SCCGWA, PNRBCC.APP_ACNM);
        CEP.TRC(SCCGWA, PNRBCC.PAYEE_AC);
        CEP.TRC(SCCGWA, PNRBCC.PAYEE_ACNM);
        PNRBCC.ODUE_FLG = '0';
        PNRBCC.FEE_FLG = PNCUCISS.FEE_FLG;
        PNRBCC.PAY_BK = PNCUCISS.PAY_BK;
        PNRBCC.USG_RMK = PNCUCISS.USG_RMK;
        PNRBCC.ISS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PNRBCC.ISS_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRBCC.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRBCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRBCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRBCC.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_WRITE_PNTBCC();
        if (pgmRtn) return;
    }
    public void B209_WRT_PNTDFPSW_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRDFPSW);
        PNRDFPSW.KEY.BILL_KND = "C00001";
        PNRDFPSW.KEY.BILL_NO = PNCUCISS.CC_NO;
        PNRDFPSW.ENCRY_NO = PNCUCISS.ENCRY_NO;
        CEP.TRC(SCCGWA, PNCUCISS.ENCRY_NO);
        CEP.TRC(SCCGWA, PNRDFPSW.ENCRY_NO);
        PNRDFPSW.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRDFPSW.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRDFPSW.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        PNRDFPSW.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_PNTDFPSW();
        if (pgmRtn) return;
    }
    public void B206_BCC_MST_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNRBCC);
        PNRBCC.KEY.BILL_KND = PNCUCISS.KND;
        PNRBCC.KEY.BILL_NO = PNCUCISS.CC_NO;
        T000_READ_PNTBCC_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_FLG == 'N') {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_BCC_NOT_FND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, PNRBCC.STS);
        CEP.TRC(SCCGWA, PNRBCC.LAST_STS);
        if (PNRBCC.STS != '1') {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_STS_NOT_NML;
            WS_ERR_INFO = "BCC-STS=" + PNRBCC.STS;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PNRBCC.UPDTBL_DATE != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_NOT_ALLOW_CANCEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (!PNRBCC.ISS_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = PNCMSG_ERROR_MSG.PN_CANCEL_TLR_MUST_EX;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        PNRBCC.LAST_STS = PNRBCC.STS;
        PNRBCC.STS = '8';
        PNRBCC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        PNRBCC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        T000_REWRITE_PNTBCC();
        if (pgmRtn) return;
    }
    public void B210_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PNCOTCEL);
        PNCOTCEL.KND = PNCUCISS.KND.charAt(0);
        PNCOTCEL.CC_NO = PNCUCISS.CC_NO;
        PNCOTCEL.STS = PNRBCC.STS;
        CEP.TRC(SCCGWA, PNCOTCEL.KND);
        CEP.TRC(SCCGWA, PNCOTCEL.CC_NO);
        CEP.TRC(SCCGWA, PNCOTCEL.STS);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT1;
        SCCFMT.DATA_PTR = PNCOTCEL;
        SCCFMT.DATA_LEN = 18;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B208_GET_DUEDATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        IBS.init(SCCGWA, SCCCLDT);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, PNVPPN01);
        BPRPRMT.KEY.TYP = "PPN01";
        BPRPRMT.KEY.CD = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = BPRPRMT.KEY.CD.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPRPRMT.KEY.CD = "0" + BPRPRMT.KEY.CD;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, PNVPPN01);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPRMT.DAT_TXT);
        BPCPRMR.FUNC = ' ';
        BPCPRMR.DAT_PTR = BPRPRMT;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, PNCFQPRD.VAL.PAY_TERM);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND) 
            || BPCPRMM.EXP_DT < SCCGWA.COMM_AREA.AC_DATE) {
            if (PNRPROD.PAY_TERM == ' ') SCCCLDT.MTHS = 0;
            else SCCCLDT.MTHS = Short.parseShort(""+PNRPROD.PAY_TERM);
        } else {
