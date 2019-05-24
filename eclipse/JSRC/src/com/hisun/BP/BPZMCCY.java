package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZMCCY {
    String JIBS_tmp_str[] = new String[10];
    String PGM_SCSSCKDT = "SCSSCKDT";
    String K_MAINTAIN_CCY = "BP-RESOURCE-CCY";
    String BP_P_CHK_CAL_CODE = "BP-P-CHK-CAL-CODE   ";
    String K_CMP_CALL_BPZQCNCI = "BP-Q-CNTY-CITY-IFO  ";
    String K_CPN_MAINT_PARM_CODE = "BP-MAINT-PARM-CODE";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    BPCRCCY BPCRCCY = new BPCRCCY();
    SCCCKDT SCCCKDT = new SCCCKDT();
    BPCOCCAL BPCOCCAL = new BPCOCCAL();
    BPCQCNCI BPCQCNCI = new BPCQCNCI();
    BPCSMPCD BPCSMPCD = new BPCSMPCD();
    SCCGWA SCCGWA;
    BPCMCCY BPCMCCY;
    public void MP(SCCGWA SCCGWA, BPCMCCY BPCMCCY) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCMCCY = BPCMCCY;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_MAINTAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPZMCCY return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCRCCY);
        IBS.init(SCCGWA, SCCCKDT);
        IBS.init(SCCGWA, BPCOCCAL);
        IBS.init(SCCGWA, BPCSMPCD);
        IBS.init(SCCGWA, BPCQCNCI);
    }
    public void B00_MAINTAIN_PROCESS() throws IOException,SQLException,Exception {
        if ((BPCMCCY.OP_FUNC != '0') 
            && (BPCMCCY.OP_FUNC != '1') 
            && (BPCMCCY.OP_FUNC != '2')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, BPCRCCY);
        if (BPCMCCY.OP_FUNC == '0') {
            IBS.init(SCCGWA, BPCRCCY);
            BPCRCCY.OP_FUNC = 'I';
            S00_QUERY_CHECK();
            R00_TRANS_DATA_PARAMETER();
            S00_CALL_BPZRCCY();
        } else if (BPCMCCY.OP_FUNC == '1') {
            IBS.init(SCCGWA, BPCRCCY);
            BPCRCCY.OP_FUNC = 'A';
            B100_CHECK_INPUT();
            R00_TRANS_DATA_PARAMETER();
            S00_CALL_BPZRCCY();
        } else if (BPCMCCY.OP_FUNC == '2') {
            IBS.init(SCCGWA, BPCRCCY);
            BPCRCCY.OP_FUNC = 'M';
            B100_CHECK_INPUT();
            R00_TRANS_DATA_PARAMETER();
            S00_CALL_BPZRCCY();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCMCCY.DATA.CNTY_CD);
        CEP.TRC(SCCGWA, BPCMCCY.DATA.CCY);
        if (BPCMCCY.DATA.CCY.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_SPACE_ERR;
            S000_ERR_MSG_PROC();
        }
        CEP.TRC(SCCGWA, BPCMCCY.DATA.CCY_CD);
        CEP.TRC(SCCGWA, BPCMCCY.DATA.CHG_CCY);
        if (BPCMCCY.DATA.CCY_CD == ' ') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_CD_SPACE_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPCMCCY.DATA.CUR_NM.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_NAME_SPACE_ERR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, SCCCKDT);
        CEP.TRC(SCCGWA, BPCMCCY.DATA.EFF_DT);
        SCCCKDT.DATE = BPCMCCY.DATA.EFF_DT;
        CEP.TRC(SCCGWA, SCCCKDT.DATE);
        SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
        SCSSCKDT1.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            CEP.TRC(SCCGWA, "DEVSOS-SCSSCKDT");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR;
            S000_ERR_MSG_PROC();
        }
        IBS.init(SCCGWA, SCCCKDT);
        SCCCKDT.DATE = BPCMCCY.DATA.EXP_DT;
        SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
        SCSSCKDT2.MP(SCCGWA, SCCCKDT);
        if (SCCCKDT.RC != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXP_DATE_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPCMCCY.DATA.EXP_DT <= BPCMCCY.DATA.EFF_DT) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EFFDT_GT_EXPDT;
            S000_ERR_MSG_PROC();
        }
        if ((BPCMCCY.DATA.DEC_MTH != '0') 
            && (BPCMCCY.DATA.DEC_MTH != '1') 
            && (BPCMCCY.DATA.DEC_MTH != '2') 
            && (BPCMCCY.DATA.DEC_MTH != '3')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DEC_MTH_ERR;
            S000_ERR_MSG_PROC();
        }
        if ((BPCMCCY.DATA.TR_FLG != 'Y') 
            && (BPCMCCY.DATA.TR_FLG != 'N')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TR_FLG_ERR;
            S000_ERR_MSG_PROC();
        }
        if ((BPCMCCY.DATA.CASH_FLG != '0') 
            && (BPCMCCY.DATA.CASH_FLG != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_FLG_ERR;
            S000_ERR_MSG_PROC();
        }
        if ((BPCMCCY.DATA.CASH_FLG == '0')) {
            if ((BPCMCCY.DATA.CASH_MTH != '0') 
                && (BPCMCCY.DATA.CASH_MTH != '1') 
                && (BPCMCCY.DATA.CASH_MTH != '2') 
                && (BPCMCCY.DATA.CASH_MTH != '3')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CASH_MTH_ERR;
                S000_ERR_MSG_PROC();
            }
            if ((BPCMCCY.DATA.RND_MTH != '0') 
                && (BPCMCCY.DATA.RND_MTH != '1') 
                && (BPCMCCY.DATA.RND_MTH != '2')) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RND_MTH_ERR;
                S000_ERR_MSG_PROC();
            }
        }
        if ((BPCMCCY.DATA.CHGU_MTH != '0') 
            && (BPCMCCY.DATA.CHGU_MTH != '1') 
            && (BPCMCCY.DATA.CHGU_MTH != '2') 
            && (BPCMCCY.DATA.CHGU_MTH != '3') 
            && (BPCMCCY.DATA.CHGU_MTH != '4') 
            && (BPCMCCY.DATA.CHGU_MTH != '5') 
            && (BPCMCCY.DATA.CHGU_MTH != '6') 
            && (BPCMCCY.DATA.CHGU_MTH != '7')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CHGU_MTH_ERR;
            S000_ERR_MSG_PROC();
        }
        if ((BPCMCCY.DATA.EXH_FLG != '0') 
            && (BPCMCCY.DATA.EXH_FLG != '1')) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_EXH_FLG_ERR;
            S000_ERR_MSG_PROC();
        }
        if ((!BPCMCCY.DATA.CALR_STD.equalsIgnoreCase("01")) 
            && (!BPCMCCY.DATA.CALR_STD.equalsIgnoreCase("02")) 
            && (!BPCMCCY.DATA.CALR_STD.equalsIgnoreCase("03")) 
            && (!BPCMCCY.DATA.CALR_STD.equalsIgnoreCase("04")) 
            && (!BPCMCCY.DATA.CALR_STD.equalsIgnoreCase("05"))) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CALR_STD_ERR;
            S000_ERR_MSG_PROC();
        }
        if (BPCMCCY.DATA.CNTY_CD.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, BPCQCNCI);
            BPCQCNCI.INPUT_DAT.CNTY_CD = BPCMCCY.DATA.CNTY_CD;
            BPCQCNCI.INPUT_DAT.CITY_CD = BPCMCCY.DATA.CITY_CD;
            S000_CALL_BPZQCNCI();
            if (BPCMCCY.DATA.CAL_CD.trim().length() == 0) {
                if (BPCMCCY.DATA.CITY_CD.trim().length() == 0) {
                    BPCMCCY.DATA.CAL_CD = BPCQCNCI.OUTPUT_CNTY_DAT.CALR_CODE;
                } else {
                    BPCMCCY.DATA.CAL_CD = BPCQCNCI.OUTPUT_CITY_DAT.I_CAL_CODE;
                }
            }
        }
        S000_CALL_BPZPCCAL();
    }
    public void S00_QUERY_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCMCCY.DATA.CCY);
        CEP.TRC(SCCGWA, BPCMCCY.DATA.CCY_CD);
        if ((BPCMCCY.DATA.CCY.trim().length() == 0) 
            && (BPCMCCY.DATA.CCY_CD == 0)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NEED_CCY_OR_CCYCD;
            S000_ERR_MSG_PROC();
        }
    }
    public void R00_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCMCCY.DATA);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCRCCY.DATA);
        CEP.TRC(SCCGWA, BPCMCCY.DATA.EFF_DT);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.EFF_DT);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CCY_CD);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CUR_NM);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.EFF_DT);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.EXP_DT);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CNTY_CD);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CITY_CD);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.UNIT_CURU_NAME);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CENT_CURU_NAME);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.RGN_CCY);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.DEC_MTH);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CASH_MTH);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.RND_MTH);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.TR_FLG);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CASH_FLG);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CHGU_MTH);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.EXH_FLG);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CALR_STD);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CAL_CD);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.UPT_DT);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.UPT_TLR);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.ISR_DAYS);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.BAL_DAYS);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CHG_CCY);
        CEP.TRC(SCCGWA, BPCRCCY.DATA.CUR_CNM);
    }
    public void S00_CALL_BPZRCCY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-RESOURCE-CCY", BPCRCCY);
        if (BPCRCCY.RC.RTNCODE == 0) {
            BPCMCCY.RC.RTNCODE = 0;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.DATA);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCMCCY.DATA);
            CEP.TRC(SCCGWA, BPCRCCY.DATA.CAL_CD);
            CEP.TRC(SCCGWA, BPCMCCY.DATA.CAL_CD);
            CEP.TRC(SCCGWA, BPCRCCY.DATA.CUR_CNM);
        } else {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRCCY.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCMCCY.RC);
        }
    }
    public void S000_CALL_BPZPCCAL() throws IOException,SQLException,Exception {
        BPCOCCAL.CAL_CODE = BPCMCCY.DATA.CAL_CD;
        IBS.CALLCPN(SCCGWA, "BP-P-CHK-CAL-CODE", BPCOCCAL);
        if (BPCOCCAL.RC.RC_CODE != 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAL_CD_NOTFOUND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZQCNCI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-Q-CNTY-CITY-IFO", BPCQCNCI);
        if (BPCQCNCI.RC.RC_CODE != 0 
            && BPCMCCY.DATA.CITY_CD.trim().length() > 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNCI.RC);
            S000_ERR_MSG_PROC();
        } else {
            if (BPCQCNCI.RC.RC_CODE != 0 
                && BPCMCCY.DATA.CITY_CD.trim().length() == 0 
                && BPCQCNCI.FLG != 'Y') {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNCI.RC);
                S000_ERR_MSG_PROC();
            } else {
                if (BPCQCNCI.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCQCNCI.RC);
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void S000_CALL_BPZSMPCD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCMCCY.DATA.CNTY_CD);
        IBS.init(SCCGWA, BPCSMPCD);
        BPCSMPCD.TYPE = "CONTY";
        BPCSMPCD.CODE = BPCMCCY.DATA.CNTY_CD;
        BPCSMPCD.EFF_DATE = BPCMCCY.DATA.EFF_DT;
        CEP.TRC(SCCGWA, BPCSMPCD.TYPE);
        CEP.TRC(SCCGWA, BPCSMPCD.CODE);
        CEP.TRC(SCCGWA, BPCSMPCD.EFF_DATE);
        BPCSMPCD.FUNC = 'Q';
        BPCSMPCD.OUTPUT_FLG = 'N';
        IBS.CALLCPN(SCCGWA, "BP-MAINT-PARM-CODE", BPCSMPCD);
        CEP.TRC(SCCGWA, BPCSMPCD);
        CEP.TRC(SCCGWA, BPCSMPCD.CHECK_RESULT);
        if (BPCSMPCD.CHECK_RESULT == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_PARM_CODE_NOTFND;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCMCCY.RC.RTNCODE != 0) {
            CEP.TRC(SCCGWA, " BPCMCCY = ");
            CEP.TRC(SCCGWA, BPCMCCY);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
