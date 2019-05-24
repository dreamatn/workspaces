package com.hisun.CM;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCSNOCK;
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

public class CMZBSP01 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "CM026";
    String WS_ERR_MSG = " ";
    String WS_CARD_NO_I = " ";
    String WS_BV_CODE_I = " ";
    String WS_BV_NO_I = " ";
    int WS_I = 0;
    char WS_OPEN_CARD_FLG = ' ';
    short WS_FILE_LEN = 0;
    CMZBSP01_WS_BATH_PARM WS_BATH_PARM = new CMZBSP01_WS_BATH_PARM();
    char WS_END_FLG = ' ';
    CMZBSP01_WS_OUTPUT WS_OUTPUT = new CMZBSP01_WS_OUTPUT();
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
    DCCSSPOT DCCSSPOT = new DCCSSPOT();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCFCDGG DCCFCDGG = new DCCFCDGG();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    CMCBSPIN CMCBSPIN = new CMCBSPIN();
    CMCSBSPO CMCSBSPO = new CMCSBSPO();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICCUST CICCUST = new CICCUST();
    BPCSNOCK BPCSNOCK = new BPCSNOCK();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCBATH SCCBATH;
    CMCSBSPI CMCSBSPI;
    public void MP(SCCGWA SCCGWA, CMCSBSPI CMCSBSPI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CMCSBSPI = CMCSBSPI;
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
        CEP.TRC(SCCGWA, "CMZBSP01 return!");
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
        WS_BATH_PARM.WS_PARM_BUSITYPE = CMCSBSPI.BUSE_TYPE;
        WS_BATH_PARM.WS_PARM_BATNO = CMCSBSPI.BAT_NO;
        WS_FILE_LEN = 1140;
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_PARM_BUSITYPE);
        CEP.TRC(SCCGWA, WS_BATH_PARM.WS_PARM_BATNO);
        CEP.TRC(SCCGWA, CMCSBSPI);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CMCSBSPI);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CMCSBSPO);
        CEP.TRC(SCCGWA, "555");
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_GET_CARDNO();
        if (pgmRtn) return;
        B050_CARD_INFOMATION_OPEN();
        if (pgmRtn) return;
        if (CMCSBSPO.AMT != 0) {
            B060_ACCOUNT_TRANSFER();
            if (pgmRtn) return;
        }
        B070_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        CEP.TRC(SCCGWA, CMCSBSPO.TR_TYPE);
        CEP.TRC(SCCGWA, CMCSBSPO.REQ_SYS_JRN);
        CEP.TRC(SCCGWA, CMCSBSPO.REQ_DATE);
        CEP.TRC(SCCGWA, CMCSBSPO.REQ_SYS);
        CEP.TRC(SCCGWA, CMCSBSPO.TR_BR);
        CEP.TRC(SCCGWA, CMCSBSPO.AP_REF);
        CEP.TRC(SCCGWA, CMCSBSPO.CHN_BUS_TYP);
        CEP.TRC(SCCGWA, CMCSBSPO.CI_NO);
        CEP.TRC(SCCGWA, CMCSBSPO.NAME);
        CEP.TRC(SCCGWA, CMCSBSPO.IDTYP);
        CEP.TRC(SCCGWA, CMCSBSPO.IDNO);
        CEP.TRC(SCCGWA, CMCSBSPO.AMT);
        CEP.TRC(SCCGWA, CMCSBSPO.PSW_TYPE);
        CEP.TRC(SCCGWA, CMCSBSPO.BUS_RMK);
        CEP.TRC(SCCGWA, CMCSBSPO.UNIT_NO);
        CEP.TRC(SCCGWA, CMCSBSPO.UNIT_NAME);
        CEP.TRC(SCCGWA, CMCSBSPO.UNIT_AC);
        CEP.TRC(SCCGWA, CMCSBSPO.REMARK);
        CEP.TRC(SCCGWA, CMCSBSPI.AC_TYP);
        CEP.TRC(SCCGWA, CMCSBSPO.AC_TYP);
        CEP.TRC(SCCGWA, CMCSBSPO.PROC_STS);
        CEP.TRC(SCCGWA, CMCSBSPO.RET_CODE);
        CEP.TRC(SCCGWA, CMCSBSPO.RET_MSG);
        CEP.TRC(SCCGWA, CMCSBSPO.DATE);
        CEP.TRC(SCCGWA, CMCSBSPO.JRNNO);
        CEP.TRC(SCCGWA, CMCSBSPO.VCH_NO);
        CEP.TRC(SCCGWA, CMCSBSPO.CARD_NO);
        CEP.TRC(SCCGWA, CMCSBSPO.INI_PSW);
        CEP.TRC(SCCGWA, CMCSBSPO.AC_TYP);
        CEP.TRC(SCCGWA, "SBSPO-CI-NO");
        CEP.TRC(SCCGWA, CMCSBSPO.CI_NO);
        CEP.TRC(SCCGWA, "SBSPO-NAME");
        CEP.TRC(SCCGWA, CMCSBSPO.NAME);
        CEP.TRC(SCCGWA, "SBSPO-IDTYP");
        CEP.TRC(SCCGWA, CMCSBSPO.IDTYP);
        CEP.TRC(SCCGWA, "SBSPO-IDNO");
        CEP.TRC(SCCGWA, CMCSBSPO.IDNO);
        CEP.TRC(SCCGWA, "SBSPO-AMT");
        CEP.TRC(SCCGWA, CMCSBSPO.AMT);
        CEP.TRC(SCCGWA, "SBSPO-PSW-TYPE");
        CEP.TRC(SCCGWA, CMCSBSPO.PSW_TYPE);
        CEP.TRC(SCCGWA, "SBSPO-BUS-RMK");
        CEP.TRC(SCCGWA, CMCSBSPO.BUS_RMK);
        CEP.TRC(SCCGWA, "SBSPO-UNIT-NO");
        CEP.TRC(SCCGWA, CMCSBSPO.UNIT_NO);
        CEP.TRC(SCCGWA, "SBSPO-UNIT-NAME");
        CEP.TRC(SCCGWA, CMCSBSPO.UNIT_NAME);
        CEP.TRC(SCCGWA, "SBSPO-UNIT-AC");
        CEP.TRC(SCCGWA, CMCSBSPO.UNIT_AC);
        CEP.TRC(SCCGWA, "SBSPO-REMARK");
        CEP.TRC(SCCGWA, CMCSBSPO.REMARK);
        if (CMCSBSPO.CI_NO.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_CINO_M_IN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCSBSPO.NAME.trim().length() > 0) {
            IBS.init(SCCGWA, CICCUST);
            CICCUST.DATA.CI_NO = CMCSBSPO.CI_NO;
            CICCUST.FUNC = 'C';
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            if (!CICCUST.O_DATA.O_CI_NM.equalsIgnoreCase(CMCSBSPO.NAME)) {
                CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_NM_ERR);
            }
        }
        if (CMCSBSPO.AMT != 0 
            && CMCSBSPO.UNIT_AC.trim().length() == 0) {
            WS_ERR_MSG = CMCMSG_ERROR_MSG.CM_ERR_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (CMCSBSPO.AC_TYP != '1' 
            && CMCSBSPO.AC_TYP != '2') {
            CEP.ERR(SCCGWA, CMCMSG_ERROR_MSG.CM_AC_TYP_ERR);
        }
    }
    public void B030_GET_CARDNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CMRBSPM);
        IBS.init(SCCGWA, CMCBSPIN);
        CMRBSPM.KEY.AP_TYPE = WS_BATH_PARM.WS_PARM_BUSITYPE;
        CMRBSPM.KEY.AP_BATNO = WS_BATH_PARM.WS_PARM_BATNO;
        T000_READ_CMTBSPM();
        if (pgmRtn) return;
