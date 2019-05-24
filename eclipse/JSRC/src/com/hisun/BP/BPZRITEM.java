package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRITEM {
    String K_BP_MMO = "BP";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    char WS_PROC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBKPM BPRBKPM = new BPRBKPM();
    BPRENTY BPRENTY = new BPRENTY();
    BPRGLM BPRGLM = new BPRGLM();
    BPRPBL BPRPBL = new BPRPBL();
    BPCPRMM BPCPRMM = new BPCPRMM();
    SCCGWA SCCGWA;
    BPCRITEM BPCRITEM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCRITEM BPCRITEM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRITEM = BPCRITEM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZRITEM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        BPCRITEM.RC.RC_MMO = K_BP_MMO;
        BPCRITEM.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCRITEM.INPUT_DATA.TYPE.equalsIgnoreCase("AMGLM")) {
            B010_UPDATE_GLM_PROC();
        } else if (BPCRITEM.INPUT_DATA.TYPE.equalsIgnoreCase("AMPBL")) {
            B020_UPDATE_PBL_PROC();
        } else if (BPCRITEM.INPUT_DATA.TYPE.equalsIgnoreCase("AMENT")) {
            B030_UPDATE_ENT_PROC();
        } else if (BPCRITEM.INPUT_DATA.TYPE.equalsIgnoreCase("AMBKP")) {
            B040_UPDATE_BKP_PROC();
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_UPDATE_GLM_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRGLM);
        IBS.init(SCCGWA, BPCPRMM);
        BPRGLM.KEY.TYP = "AMGLM";
        BPRGLM.KEY.CD = BPCRITEM.INPUT_DATA.CODE;
        IBS.CPY2CLS(SCCGWA, BPRGLM.KEY.CD, BPRGLM.KEY.REDEFINES6);
        BPCPRMM.FUNC = '4';
        WS_PROC_FLG = '1';
        S000_CALL_BPZPRMM();
        CEP.TRC(SCCGWA, BPRGLM.KEY.CD);
        CEP.TRC(SCCGWA, BPCRITEM.INPUT_DATA.OLD_ITEM);
        CEP.TRC(SCCGWA, BPCRITEM.INPUT_DATA.NEW_ITEM);
        for (WS_IDX = 1; WS_IDX <= 60; WS_IDX += 1) {
            CEP.TRC(SCCGWA, WS_IDX);
            CEP.TRC(SCCGWA, BPRGLM.DATA_TXT.REL_ITMS[WS_IDX-1].ITM_NO);
            if (BPRGLM.DATA_TXT.REL_ITMS[WS_IDX-1].ITM_NO.equalsIgnoreCase(BPCRITEM.INPUT_DATA.OLD_ITEM)) {
                BPRGLM.DATA_TXT.REL_ITMS[WS_IDX-1].ITM_NO = BPCRITEM.INPUT_DATA.NEW_ITEM;
                BPRGLM.DATA_TXT.REL_ITMS[WS_IDX-1].REMARK = BPCRITEM.INPUT_DATA.NEW_REMARK;
            }
        }
        if (BPRGLM.DATA_TXT.REAL_GL.equalsIgnoreCase(BPCRITEM.INPUT_DATA.OLD_ITEM)) {
            BPRGLM.DATA_TXT.REAL_GL = BPCRITEM.INPUT_DATA.NEW_ITEM;
        }
        if (BPRGLM.DATA_TXT.MEMO_GL.equalsIgnoreCase(BPCRITEM.INPUT_DATA.OLD_ITEM)) {
            BPRGLM.DATA_TXT.MEMO_GL = BPCRITEM.INPUT_DATA.NEW_ITEM;
        }
        BPRGLM.DATA_TXT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRGLM.DATA_TXT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRGLM.DATA_TXT.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPRGLM.DATA_TXT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRGLM.DATA_TXT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCPRMM.FUNC = '2';
        WS_PROC_FLG = '1';
        S000_CALL_BPZPRMM();
    }
    public void B020_UPDATE_PBL_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPBL);
        IBS.init(SCCGWA, BPCPRMM);
        BPRPBL.KEY.TYPE = "AMPBL";
        BPRPBL.KEY.CD = BPCRITEM.INPUT_DATA.CODE;
        IBS.CPY2CLS(SCCGWA, BPRPBL.KEY.CD, BPRPBL.KEY.REDEFINES6);
        BPCPRMM.FUNC = '4';
        WS_PROC_FLG = '2';
        S000_CALL_BPZPRMM();
        for (WS_IDX = 1; WS_IDX <= 60; WS_IDX += 1) {
            if (BPRPBL.TEXT.DATA[WS_IDX-1].ITM_NO.equalsIgnoreCase(BPCRITEM.INPUT_DATA.OLD_ITEM)) {
                BPRPBL.TEXT.DATA[WS_IDX-1].ITM_NO = BPCRITEM.INPUT_DATA.NEW_ITEM;
            }
        }
        BPRPBL.TEXT.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPRPBL.TEXT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRPBL.TEXT.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRPBL.TEXT.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCPRMM.FUNC = '2';
        WS_PROC_FLG = '2';
        S000_CALL_BPZPRMM();
    }
    public void B030_UPDATE_ENT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRENTY.BASIC_INFO);
        BPRENTY.BASIC_INFO.KEY.TYPE = "AMENT";
        BPRENTY.BASIC_INFO.KEY.CODE = BPCRITEM.INPUT_DATA.CODE;
        IBS.CPY2CLS(SCCGWA, BPRENTY.BASIC_INFO.KEY.CODE, BPRENTY.BASIC_INFO.KEY.REDEFINES7);
        BPCPRMM.FUNC = '4';
        WS_PROC_FLG = '3';
        S000_CALL_BPZPRMM();
        for (WS_IDX = 1; WS_IDX <= BPRENTY.DATA_TXT.CNT; WS_IDX += 1) {
            if (BPRENTY.DATA_TXT.EVENT_DATA[WS_IDX-1].ITM_CODE.equalsIgnoreCase(BPCRITEM.INPUT_DATA.OLD_ITEM)) {
                BPRENTY.DATA_TXT.EVENT_DATA[WS_IDX-1].ITM_CODE = BPCRITEM.INPUT_DATA.NEW_ITEM;
            }
        }
        BPRENTY.DATA_TXT.LSTUPD_INFO.UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
        BPRENTY.DATA_TXT.LSTUPD_INFO.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRENTY.DATA_TXT.LSTUPD_INFO.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        BPRENTY.DATA_TXT.LSTUPD_INFO.SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
        BPRENTY.DATA_TXT.LSTUPD_INFO.SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
        BPCPRMM.FUNC = '2';
        WS_PROC_FLG = '3';
        S000_CALL_BPZPRMM();
    }
    public void B040_UPDATE_BKP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMM);
        IBS.init(SCCGWA, BPRBKPM);
        BPRBKPM.KEY.TYPE = "AMBKP";
        BPRBKPM.KEY.CD = BPCRITEM.INPUT_DATA.CODE;
        IBS.CPY2CLS(SCCGWA, BPRBKPM.KEY.CD, BPRBKPM.KEY.REDEFINES6);
        BPCPRMM.FUNC = '4';
        WS_PROC_FLG = '4';
        S000_CALL_BPZPRMM();
        for (WS_IDX = 1; WS_IDX <= 10; WS_IDX += 1) {
            if (BPRBKPM.DAT_TXT.DATA[WS_IDX-1].BOOK_FLG.equalsIgnoreCase(BPCRITEM.INPUT_DATA.BOOK_FLG)) {
                if (BPRBKPM.DAT_TXT.DATA[WS_IDX-1].REAL_SUS_ITM.equalsIgnoreCase(BPCRITEM.INPUT_DATA.OLD_ITEM)) {
                    BPRBKPM.DAT_TXT.DATA[WS_IDX-1].REAL_SUS_ITM = BPCRITEM.INPUT_DATA.NEW_ITEM;
                }
                if (BPRBKPM.DAT_TXT.DATA[WS_IDX-1].MEMO_SUS_ITM.equalsIgnoreCase(BPCRITEM.INPUT_DATA.OLD_ITEM)) {
                    BPRBKPM.DAT_TXT.DATA[WS_IDX-1].MEMO_SUS_ITM = BPCRITEM.INPUT_DATA.NEW_ITEM;
                }
                if (BPRBKPM.DAT_TXT.DATA[WS_IDX-1].PL_ITM.equalsIgnoreCase(BPCRITEM.INPUT_DATA.OLD_ITEM)) {
                    BPRBKPM.DAT_TXT.DATA[WS_IDX-1].PL_ITM = BPCRITEM.INPUT_DATA.NEW_ITEM;
                }
                if (BPRBKPM.DAT_TXT.DATA[WS_IDX-1].CRS_BR_ITM.equalsIgnoreCase(BPCRITEM.INPUT_DATA.OLD_ITEM)) {
                    BPRBKPM.DAT_TXT.DATA[WS_IDX-1].CRS_BR_ITM = BPCRITEM.INPUT_DATA.NEW_ITEM;
                }
                BPRBKPM.DAT_TXT.DATA[WS_IDX-1].UPD_TEL = SCCGWA.COMM_AREA.TL_ID;
                BPRBKPM.DAT_TXT.DATA[WS_IDX-1].UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                BPRBKPM.DAT_TXT.DATA[WS_IDX-1].SUP_TEL1 = SCCGWA.COMM_AREA.SUP1_ID;
                BPRBKPM.DAT_TXT.DATA[WS_IDX-1].SUP_TEL2 = SCCGWA.COMM_AREA.SUP2_ID;
            }
        }
        BPCPRMM.FUNC = '2';
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        WS_PROC_FLG = '4';
        S000_CALL_BPZPRMM();
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        if (WS_PROC_FLG == '1') {
            BPCPRMM.DAT_PTR = BPRGLM;
        } else if (WS_PROC_FLG == '2') {
            BPCPRMM.DAT_PTR = BPRPBL;
        } else if (WS_PROC_FLG == '3') {
            BPCPRMM.DAT_PTR = BPRENTY;
        } else if (WS_PROC_FLG == '4') {
            BPCPRMM.DAT_PTR = BPRBKPM;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_ERROR;
            S000_ERR_MSG_PROC();
        }
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            S000_ERR_MSG_PROC();
        }
        WS_PROC_FLG = ' ';
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRITEM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRITEM = ");
            CEP.TRC(SCCGWA, BPCRITEM);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
