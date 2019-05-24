package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.FX.FXCMSG_ERROR_MSG;
import com.hisun.FX.FXRDIRFX;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCKDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGAPV;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGMSG;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZSFX {
    boolean pgmRtn = false;
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
    String CPN_INQ_EXR_RATE = "BP-INQ-FX-QTP     ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_R_EXRM = "BP-R-EXRD-M       ";
    String CPN_R_TQPS = "BP-R-TQP-B        ";
    String CPN_R_SQTPH = "BP-R-SQTPH-B      ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG      ";
    String CPN_R_ICSM = "BP-R-ICSP-M       ";
    String PGM_SCSSCKDT = "SCSSCKDT";
    double K_AMT_MAX = 9999999999999.99;
    int WK_BR_HD = 999999;
    String WS_CCY = " ";
    char WS_CMP_FLG = ' ';
    double WS_CS_BUY_P = 0;
    double WS_FX_BUY_P = 0;
    double WS_CS_SELL_P = 0;
    double WS_FX_SELL_P = 0;
    double WS_SP_RATE = 0;
    double WS_PRE_SP = 0;
    double WS_TMP_SP = 0;
    int WS_BR = 0;
    BPZSFX_WS_TM WS_TM = new BPZSFX_WS_TM();
    double WT_TMP_RATE = 0;
    double WK_TMP_RATE = 0;
    double WS_TMP_RATE = 0;
    double WS_INP_RATE = 0;
    double WS_OUT_RATE = 0;
    short WS_DEC_PNT = 0;
    char WS_RND_FLG = ' ';
    double WS_TMP_AMT = 0;
    double WK_TMP_AMT = 0;
    double WS_INP_AMT = 0;
    double WS_OUT_AMT = 0;
    short WS_P = 0;
    long WS_M = 0;
    double WS_M_1 = 0;
    double WS_M_2 = 0;
    double WS_M_3 = 0;
    short WS_PNT = 0;
    String WS_PNT_C = " ";
    double WK_PNT = 0;
    char WS_RND = ' ';
    double WS_TRN_RATE = 0;
    int WS_UNT = 0;
    double WS_CS_BUY = 0;
    double WS_CS_SELL = 0;
    double WS_FX_BUY = 0;
    double WS_FX_SELL = 0;
    double WS_LOC_MID = 0;
    double WS_CCS_BUY = 0;
    double WS_CCS_SELL = 0;
    double WS_CFX_BUY = 0;
    double WS_CFX_SELL = 0;
    int WS_EFF_DT = 0;
    int WS_EFF_TM = 0;
    int WS_UNT_1 = 0;
    double WS_CS_BUY_1 = 0;
    double WS_CS_SELL_1 = 0;
    double WS_FX_BUY_1 = 0;
    double WS_FX_SELL_1 = 0;
    double WS_LOC_MID_1 = 0;
    double WS_CCS_BUY_1 = 0;
    double WS_CCS_SELL_1 = 0;
    double WS_CFX_BUY_1 = 0;
    double WS_CFX_SELL_1 = 0;
    int WS_EFF_DT_1 = 0;
    int WS_EFF_TM_1 = 0;
    int WS_UNT_2 = 0;
    double WS_CS_BUY_2 = 0;
    double WS_CS_SELL_2 = 0;
    double WS_FX_BUY_2 = 0;
    double WS_FX_SELL_2 = 0;
    double WS_LOC_MID_2 = 0;
    double WS_CCS_BUY_2 = 0;
    double WS_CCS_SELL_2 = 0;
    double WS_CFX_BUY_2 = 0;
    double WS_CFX_SELL_2 = 0;
    int WS_EFF_DT_2 = 0;
    int WS_EFF_TM_2 = 0;
    int WS_B_UNIT = 1;
    int WS_S_UNIT = 1;
    double WS_AMT_1 = 0;
    double WS_AMT_2 = 0;
    double WS_AMT_3 = 0;
    short WS_NUM2_1 = 0;
    short WS_NUM2_2 = 0;
    short WS_NUM2_3 = 0;
    int WS_COUNT_NO1 = 0;
    int WS_COUNT_NO2 = 0;
    FXCMSG_ERROR_MSG FXCMSG_ERROR_MSG = new FXCMSG_ERROR_MSG();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    BPCQCCY BPCQCCYB = new BPCQCCY();
    BPCQCCY BPCQCCYS = new BPCQCCY();
    SCCMSG SCCMSG = new SCCMSG();
    BPRICSP BPRICSP = new BPRICSP();
    BPCTICSM BPCTICSM = new BPCTICSM();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCOIEC BPCOIEC = new BPCOIEC();
    BPCIFQ BPCIFQ = new BPCIFQ();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCBSP SCCBSP = new SCCBSP();
    BPCEXGM BPCEXGM = new BPCEXGM();
    BPCTEXRM BPCTEXRM = new BPCTEXRM();
    BPCREXRS BPCREXRS = new BPCREXRS();
    BPCTSPCM BPCTSPCM = new BPCTSPCM();
    BPCPFAV BPCPFAV = new BPCPFAV();
    BPREXRD BPREXRD = new BPREXRD();
    BPREXRD BPREXRD1 = new BPREXRD();
    BPREXRD BPREXRD2 = new BPREXRD();
    BPRSQTPH BPRSQTPH = new BPRSQTPH();
    BPRTQP BPRTQP = new BPRTQP();
    BPREXG BPREXG = new BPREXG();
    FXRDIRFX FXRDIRFX = new FXRDIRFX();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGAPV SCCGAPV;
    SCCGMSG SCCGMSG;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCFX BPCFX;
    public void MP(SCCGWA SCCGWA, BPCFX BPCFX) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFX = BPCFX;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSFX return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGAPV = (SCCGAPV) GWA_SC_AREA.APVL_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGMSG = (SCCGMSG) GWA_SC_AREA.MSG_AREA_PTR;
        CEP.TRC(SCCGWA, BPCRBANK.LOC_CCY1);
        CEP.TRC(SCCGWA, BPCFX.BUY_AMT);
        CEP.TRC(SCCGWA, BPCFX.SELL_AMT);
        CEP.TRC(SCCGWA, BPCFX.EXR_TYPE);
        CEP.TRC(SCCGWA, BPCFX.BUY_CCY);
        CEP.TRC(SCCGWA, BPCFX.B_CASH_FLG);
        CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
        CEP.TRC(SCCGWA, BPCFX.S_CASH_FLG);
        WS_B_UNIT = 1;
        WS_S_UNIT = 1;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B001_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFX.FUNC);
        if (BPCFX.FUNC == '1') {
            B020_CAL_RATE_BY_AMT();
            if (pgmRtn) return;
        } else if (BPCFX.FUNC == '2') {
            B010_GET_TRN_RATE();
            if (pgmRtn) return;
        } else if (BPCFX.FUNC == '3'
            || BPCFX.FUNC == '4') {
            B010_GET_TRN_RATE();
            if (pgmRtn) return;
            B030_CAL_AMT();
            if (pgmRtn) return;
        } else if (BPCFX.FUNC == '5') {
            B030_CAL_AMT();
            if (pgmRtn) return;
        } else {
        }
        if (BPCFX.BUY_CCY.equalsIgnoreCase(BPCFX.SELL_CCY)) {
            if (BPCFX.EXR_CODE == null) BPCFX.EXR_CODE = "";
            JIBS_tmp_int = BPCFX.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCFX.EXR_CODE += " ";
            if (BPCFX.BUY_CCY == null) BPCFX.BUY_CCY = "";
            JIBS_tmp_int = BPCFX.BUY_CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCFX.BUY_CCY += " ";
            BPCFX.EXR_CODE = BPCFX.BUY_CCY + BPCFX.EXR_CODE.substring(3);
            if (BPCFX.EXR_CODE == null) BPCFX.EXR_CODE = "";
            JIBS_tmp_int = BPCFX.EXR_CODE.length();
            for (int i=0;i<6-JIBS_tmp_int;i++) BPCFX.EXR_CODE += " ";
            if (BPCFX.BUY_CCY == null) BPCFX.BUY_CCY = "";
            JIBS_tmp_int = BPCFX.BUY_CCY.length();
            for (int i=0;i<3-JIBS_tmp_int;i++) BPCFX.BUY_CCY += " ";
            BPCFX.EXR_CODE = BPCFX.EXR_CODE.substring(0, 4 - 1) + BPCFX.BUY_CCY + BPCFX.EXR_CODE.substring(4 + 3 - 1);
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFX);
        if ((BPCFX.FUNC != '0' 
            && BPCFX.FUNC != '1' 
            && BPCFX.FUNC != '2' 
            && BPCFX.FUNC != '5' 
            && BPCFX.FUNC != '3' 
            && BPCFX.FUNC != '4')) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_FUNC_ERROR);
        }
        if (BPCFX.EXR_TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXRGROP);
        } else {
            B005_CHK_EXR_TYPE();
            if (pgmRtn) return;
        }
        if (BPCFX.FUNC == '2' 
            || BPCFX.FUNC == '1' 
            || BPCFX.FUNC == '4' 
            || BPCFX.FUNC == '5' 
            || BPCFX.FUNC == '3') {
            B011_05_CCY_CHK();
            if (pgmRtn) return;
        }
        if (BPCFX.FUNC == '1') {
            if (BPCFX.BUY_AMT == 0 
                || BPCFX.SELL_AMT == 0 
                || BPCFX.BUY_CCY.trim().length() == 0 
                || BPCFX.SELL_CCY.trim().length() == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CAL_RATE_NO_CCY_AMT);
            }
        }
        if (BPCFX.FUNC == '3' 
            || BPCFX.FUNC == '4' 
            || BPCFX.FUNC == '5') {
            if (BPCFX.BUY_AMT == 0 
                && BPCFX.SELL_AMT == 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_NOT_INPUT_AMT);
            }
            if (BPCFX.BUY_AMT != 0 
                && BPCFX.SELL_AMT != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_EX_AMT_ERR);
            }
        }
        CEP.TRC(SCCGWA, BPCFX.EFF_DATE);
        if (BPCFX.EFF_DATE == 0 
            && BPCFX.EFF_TIME != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR);
        }
        if (BPCFX.EFF_DATE != 0 
            && BPCFX.EFF_TIME == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TIME_ERR);
        }
        if (BPCFX.EFF_DATE != 0) {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPCFX.EFF_DATE;
            CEP.TRC(SCCGWA, SCCCKDT);
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR);
            }
        }
        CEP.TRC(SCCGWA, BPCFX.EFF_TIME);
        if (BPCFX.EFF_TIME != 0) {
            IBS.CPY2CLS(SCCGWA, BPCFX.EFF_TIME+"", WS_TM);
            if (WS_TM.WS_TM_HH > 23 
                || WS_TM.WS_TM_MM > 59 
                || WS_TM.WS_TM_SS > 59) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TIME_ERR);
            }
        }
        if (BPCFX.BR == 0) {
            BPCFX.BR = WK_BR_HD;
        } else {
            WS_BR = BPCFX.BR;
            R000_CHECK_BRANCH();
            if (pgmRtn) return;
        }
        if (BPCFX.SELL_CCY.equalsIgnoreCase("156")) {
            CEP.TRC(SCCGWA, BPCFX.S_CASH_FLG);
            if (BPCFX.S_CASH_FLG != ' ') {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_CNY_ERR);
            }
        }
        if (BPCFX.BUY_CCY.equalsIgnoreCase("156")) {
            CEP.TRC(SCCGWA, BPCFX.B_CASH_FLG);
            if (BPCFX.B_CASH_FLG != ' ') {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_CNY_ERR);
            }
        }
        if (BPCFX.B_CASH_FLG == ' ' 
            && !BPCFX.BUY_CCY.equalsIgnoreCase("156")) {
            BPCFX.B_CASH_FLG = '1';
        }
        if (BPCFX.S_CASH_FLG == ' ' 
            && !BPCFX.SELL_CCY.equalsIgnoreCase("156")) {
            BPCFX.S_CASH_FLG = '1';
        }
        if (!BPCFX.SELL_CCY.equalsIgnoreCase("156")) {
            if (BPCFX.S_CASH_FLG != '0' 
                && BPCFX.S_CASH_FLG != '1') {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_FCY_ERR);
            }
        }
        if (!BPCFX.BUY_CCY.equalsIgnoreCase("156")) {
            if (BPCFX.B_CASH_FLG != '0' 
                && BPCFX.B_CASH_FLG != '1') {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_CASH_FLG_FCY_ERR);
            }
        }
    }
    public void B005_CHK_EXR_TYPE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFX.EXR_TYPE);
        if (BPCFX.EXR_TYPE.trim().length() > 0) {
            IBS.init(SCCGWA, BPREXRT);
            IBS.init(SCCGWA, BPCPRMR);
            BPREXRT.KEY.TYP = "EXRT";
            BPREXRT.KEY.CD = BPCFX.EXR_TYPE;
            BPCPRMR.DAT_PTR = BPREXRT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPRMR.RC.RC_RTNCODE);
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP);
            }
        }
    }
    public void B011_05_CCY_CHK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCFX.BUY_CCY);
        CEP.TRC(SCCGWA, BPCFX.SELL_CCY);
        if (BPCFX.PRD_CD.equalsIgnoreCase("FX00000001")) {
            if (!BPCFX.BUY_CCY.equalsIgnoreCase("156") 
                && !BPCFX.SELL_CCY.equalsIgnoreCase("156")) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_PROC_CNY);
            }
        }
        if (BPCFX.PRD_CD.equalsIgnoreCase("FX00000002")) {
            if (BPCFX.BUY_CCY.equalsIgnoreCase("156") 
                || BPCFX.SELL_CCY.equalsIgnoreCase("156")) {
                CEP.ERR(SCCGWA, FXCMSG_ERROR_MSG.FX_PROC_NOT_CNY);
            }
        }
        if (BPCFX.BUY_CCY.trim().length() > 0) {
            WS_CCY = BPCFX.BUY_CCY;
            R000_CHECK_CCY();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCQCCY.DATA);
