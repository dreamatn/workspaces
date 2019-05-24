package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZIFLTN {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZIFLTN";
    String K_PARM_NRULE = "NRULE";
    String K_PRDT_INF_MAINT = "BR-BRW-LTHL-HIS";
    String K_T_MAINT_PRDT_MENU = "BP-BRW-RTHL-HIS";
    String CPN_CALL_BPZPCKWD = "BP-P-CHK-WORK-DAY   ";
    String CPN_CALL_BPZPRMB = "BP-PARM-BROWSE      ";
    String CPN_CALL_BPZUFLT = "BP-UPD-FLT-STS      ";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    short WS_CNT = 0;
    short WS_CNT_1 = 0;
    short WS_LEN = 0;
    short WS_REQID = 0;
    char WS_FOUND_FLG = ' ';
    char WS_DATE1_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCNRULE BPCNRULE = new BPCNRULE();
    BPCPRMB BPCPRMB = new BPCPRMB();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCOCKWD BPCOCKWD = new BPCOCKWD();
    BPCUFLT BPCUFLT = new BPCUFLT();
    BPCOXCAL BPCOXCAL = new BPCOXCAL();
    BPCSLTHL BPCSLTHL = new BPCSLTHL();
    BPCTLTHL BPCTLTHL = new BPCTLTHL();
    BPRFLTHL BPRFLTHL = new BPRFLTHL();
    BPCPQBCH BPCPQBCH = new BPCPQBCH();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    SCCGWA SCCGWA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPCIFLTN BPCIFLTN;
    public void MP(SCCGWA SCCGWA, BPCIFLTN BPCIFLTN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCIFLTN = BPCIFLTN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZIFLTN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCKWD);
        IBS.init(SCCGWA, BPCUFLT);
        IBS.init(SCCGWA, BPCNRULE);
        IBS.init(SCCGWA, BPRPRMT);
        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NORMAL;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B030_BROWSE_FLT_CODE_INFO();
        if (pgmRtn) return;
    }
    public void B010_GET_DATE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPGDIN);
        BPCPGDIN.INPUT_DATA.CCY = BPCRBANK.LOC_CCY1;
        BPCPGDIN.INPUT_DATA.FUNC = '1';
        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
        BPCPGDIN.INPUT_DATA.DATE_1 = BPCIFLTN.DATE;
        CEP.TRC(SCCGWA, BPCIFLTN.DATE);
        CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.DATE_1);
        S000_CALL_BPZPGDIN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE1_EXG);
    }
    public void B011_GET_DATE_INFO() throws IOException,SQLException,Exception {
        BPCPGDIN.INPUT_DATA.FUNC = '1';
        BPCPGDIN.INPUT_DATA.DATE_TYPE = 'B';
        CEP.TRC(SCCGWA, BPCIFLTN.DATE);
        CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.DATE_1);
        CEP.TRC(SCCGWA, BPCPGDIN);
        S000_CALL_BPZPGDIN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPGDIN.OUTPUT_DATA.DATE1_EXG);
    }
    public void B030_BROWSE_FLT_CODE_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMB);
        S000_TRANS_BPCNRULE();
        if (pgmRtn) return;
        BPCPRMB.FUNC = '0';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        WS_REQID = BPCPRMB.REQID;
        CEP.TRC(SCCGWA, " START BR");
        CEP.TRC(SCCGWA, BPCPRMB.REQID);
        CEP.TRC(SCCGWA, WS_REQID);
        while (WS_FOUND_FLG != 'N') {
            BPCPRMB.FUNC = '1';
            S000_CALL_BPZPRMB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCPRMB.REQID);
            CEP.TRC(SCCGWA, BPCNRULE.TYP);
            CEP.TRC(SCCGWA, BPCNRULE.REDEFINES3.FLT_CD);
            CEP.TRC(SCCGWA, BPCNRULE.DESC);
            CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[1-1].FLT_ITEM);
            CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[2-1].FLT_ITEM);
            if (WS_FOUND_FLG != 'N') {
                B010_INQ_NRULETYPE();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, " AFTER  UPDATE ");
            CEP.TRC(SCCGWA, BPCPRMB.REQID);
        }
        BPCPRMB.FUNC = '2';
        CEP.TRC(SCCGWA, " BEFORE END BR");
        CEP.TRC(SCCGWA, BPCPRMB.REQID);
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void S000_TRANS_BPCNRULE() throws IOException,SQLException,Exception {
        BPCNRULE.TYP = K_PARM_NRULE;
        BPRPRMT.KEY.TYP = K_PARM_NRULE;
        BPCPRMB.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_LEN = 450;
        BPCPRMB.DAT_PTR = BPRPRMT;
    }
    public void T001_WOK_BPTLTHL() throws IOException,SQLException,Exception {
        WS_DATE1_FLG = 'Y';
        if (BPRFLTHL.CALD_CD1.trim().length() > 0 
            || BPRFLTHL.CITY_CD1.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.DATE_1 = BPCIFLTN.DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = BPRFLTHL.CALD_CD1;
            BPCPGDIN.INPUT_DATA.CITY_CD = BPRFLTHL.CITY_CD1;
            CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.CAL_CD);
            CEP.TRC(SCCGWA, BPCPGDIN.INPUT_DATA.CITY_CD);
            B011_GET_DATE_INFO();
            if (pgmRtn) return;
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N' 
                || BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                WS_DATE1_FLG = 'S';
            }
        }
        if (BPRFLTHL.CALD_CD2.trim().length() > 0 
            || BPRFLTHL.CITY_CD2.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.DATE_1 = BPCIFLTN.DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = BPRFLTHL.CALD_CD2;
            BPCPGDIN.INPUT_DATA.CITY_CD = BPRFLTHL.CITY_CD2;
            B011_GET_DATE_INFO();
            if (pgmRtn) return;
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N' 
                || BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                WS_DATE1_FLG = 'S';
            }
        }
        if (BPRFLTHL.CALD_CD3.trim().length() > 0 
            || BPRFLTHL.CITY_CD3.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.DATE_1 = BPCIFLTN.DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = BPRFLTHL.CALD_CD3;
            BPCPGDIN.INPUT_DATA.CITY_CD = BPRFLTHL.CITY_CD3;
            B011_GET_DATE_INFO();
            if (pgmRtn) return;
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N' 
                || BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                WS_DATE1_FLG = 'S';
            }
        }
        if (BPRFLTHL.CALD_CD4.trim().length() > 0 
            || BPRFLTHL.CITY_CD4.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.DATE_1 = BPCIFLTN.DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = BPRFLTHL.CALD_CD4;
            BPCPGDIN.INPUT_DATA.CITY_CD = BPRFLTHL.CITY_CD4;
            B011_GET_DATE_INFO();
            if (pgmRtn) return;
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N' 
                || BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                WS_DATE1_FLG = 'S';
            }
        }
        if (BPRFLTHL.CALD_CD5.trim().length() > 0 
            || BPRFLTHL.CITY_CD5.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.DATE_1 = BPCIFLTN.DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = BPRFLTHL.CALD_CD5;
            BPCPGDIN.INPUT_DATA.CITY_CD = BPRFLTHL.CITY_CD5;
            B011_GET_DATE_INFO();
            if (pgmRtn) return;
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N' 
                || BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                WS_DATE1_FLG = 'S';
            }
        }
        if (BPRFLTHL.CALD_CD6.trim().length() > 0 
            || BPRFLTHL.CITY_CD6.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.DATE_1 = BPCIFLTN.DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = BPRFLTHL.CALD_CD6;
            BPCPGDIN.INPUT_DATA.CITY_CD = BPRFLTHL.CITY_CD6;
            B011_GET_DATE_INFO();
            if (pgmRtn) return;
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N' 
                || BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                WS_DATE1_FLG = 'S';
            }
        }
        if (BPRFLTHL.CALD_CD7.trim().length() > 0 
            || BPRFLTHL.CITY_CD7.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.DATE_1 = BPCIFLTN.DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = BPRFLTHL.CALD_CD7;
            BPCPGDIN.INPUT_DATA.CITY_CD = BPRFLTHL.CITY_CD7;
            B011_GET_DATE_INFO();
            if (pgmRtn) return;
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N' 
                || BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                WS_DATE1_FLG = 'S';
            }
        }
        if (BPRFLTHL.CALD_CD8.trim().length() > 0 
            || BPRFLTHL.CITY_CD8.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.DATE_1 = BPCIFLTN.DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = BPRFLTHL.CALD_CD8;
            BPCPGDIN.INPUT_DATA.CITY_CD = BPRFLTHL.CITY_CD8;
            B011_GET_DATE_INFO();
            if (pgmRtn) return;
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N' 
                || BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                WS_DATE1_FLG = 'S';
            }
        }
        if (BPRFLTHL.CALD_CD9.trim().length() > 0 
            || BPRFLTHL.CITY_CD9.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.DATE_1 = BPCIFLTN.DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = BPRFLTHL.CALD_CD9;
            BPCPGDIN.INPUT_DATA.CITY_CD = BPRFLTHL.CITY_CD9;
            B011_GET_DATE_INFO();
            if (pgmRtn) return;
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N' 
                || BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                WS_DATE1_FLG = 'S';
            }
        }
        if (BPRFLTHL.CALD_CD10.trim().length() > 0 
            || BPRFLTHL.CITY_CD10.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPGDIN);
            BPCPGDIN.INPUT_DATA.DATE_1 = BPCIFLTN.DATE;
            BPCPGDIN.INPUT_DATA.CAL_CD = BPRFLTHL.CALD_CD10;
            BPCPGDIN.INPUT_DATA.CITY_CD = BPRFLTHL.CITY_CD10;
            B011_GET_DATE_INFO();
            if (pgmRtn) return;
            if (BPCPGDIN.OUTPUT_DATA.DATE1_EXG == 'N' 
                || BPCPGDIN.OUTPUT_DATA.DATE1_FLG == 'H') {
                WS_DATE1_FLG = 'S';
            }
        }
    }
    public void B010_INQ_NRULETYPE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCTLTHL);
        BPCTLTHL.CLEAR_HOLD_TYPE = BPCNRULE.REDEFINES3.FLT_CD;
        BPCTLTHL.CCY = "%%%";
        CEP.TRC(SCCGWA, BPCTLTHL.CLEAR_HOLD_TYPE);
        CEP.TRC(SCCGWA, BPCTLTHL.CCY);
        BPCTLTHL.INFO.FUNC = 'B';
        BPCTLTHL.INFO.OPT = 'S';
        BPCTLTHL.INFO.PTR = BPRFLTHL;
        BPCTLTHL.INFO.DATA_LEN = 113;
        S000_CALL_BPZTLTHL();
        if (pgmRtn) return;
        for (WS_CNT_1 = 1; BPCTLTHL.RETURN_INFO != 'N'; WS_CNT_1 += 1) {
            BPCTLTHL.INFO.OPT = 'N';
            S000_CALL_BPZTLTHL();
            if (pgmRtn) return;
            if (BPCTLTHL.RETURN_INFO != 'N') {
                T001_WOK_BPTLTHL();
                if (pgmRtn) return;
                T000_UPDATE_FLOAT_STATUS();
                if (pgmRtn) return;
            }
        }
        BPCTLTHL.INFO.OPT = 'E';
        S000_CALL_BPZTLTHL();
        if (pgmRtn) return;
    }
    public void T000_UPDATE_FLOAT_STATUS() throws IOException,SQLException,Exception {
        WS_CNT = 1;
        for (WS_CNT = 1; WS_CNT <= 10 
            && BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM.trim().length() != 0; WS_CNT += 1) {
            CEP.TRC(SCCGWA, WS_CNT);
            CEP.TRC(SCCGWA, BPCNRULE.REDEFINES3.FLT_CD);
            CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM);
            CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].NOR_RULE);
            CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].TO_ITEM1);
            CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].SPC_RULE);
            CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].TO_ITEM2);
            if (WS_DATE1_FLG != 'S') {
                BPCUFLT.FLT_CODE = BPCNRULE.REDEFINES3.FLT_CD;
                BPCUFLT.FLT_ITEM = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM;
                BPCUFLT.FLT_STS = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].NOR_RULE;
                BPCUFLT.TO_FLT = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].TO_ITEM1;
                BPCUFLT.CCY = BPRFLTHL.KEY.CCY;
            } else {
                BPCUFLT.FLT_CODE = BPCNRULE.REDEFINES3.FLT_CD;
                BPCUFLT.FLT_ITEM = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].FLT_ITEM;
                BPCUFLT.FLT_STS = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].SPC_RULE;
                BPCUFLT.TO_FLT = BPCNRULE.DATA.DAT_TXT[WS_CNT-1].TO_ITEM2;
                BPCUFLT.CCY = BPRFLTHL.KEY.CCY;
            }
            CEP.TRC(SCCGWA, BPCPRMB.REQID);
            CEP.TRC(SCCGWA, BPCNRULE.DATA.DAT_TXT[WS_CNT-1].USE_FLG);
            if (BPCNRULE.DATA.DAT_TXT[WS_CNT-1].USE_FLG == 'Y' 
                && BPCNRULE.REDEFINES3.FLT_CD.trim().length() > 0) {
                CEP.TRC(SCCGWA, BPCUFLT.FLT_STS);
                if (BPCUFLT.FLT_STS != 'B' 
                    && BPCUFLT.FLT_STS != 'D' 
                    && BPCUFLT.FLT_STS != 'T') {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NRULE_ERR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                S000_CALL_BPZUFLT();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZTLTHL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_T_MAINT_PRDT_MENU, BPCTLTHL);
        if (BPCTLTHL.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTLTHL.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCSLTHL.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPCKWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPCKWD, BPCOCKWD);
        CEP.TRC(SCCGWA, BPCOCKWD.RC);
        if (BPCOCKWD.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCOCKWD.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NORMAL;
        }
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPRMB, BPCPRMB);
        CEP.TRC(SCCGWA, BPCPRMB.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCKWD.RC.RC_CODE);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            BPCNRULE.TYP = BPRPRMT.KEY.TYP;
            BPCNRULE.CD = BPRPRMT.KEY.CD;
            IBS.CPY2CLS(SCCGWA, BPCNRULE.CD, BPCNRULE.REDEFINES3);
            BPCNRULE.DESC = BPRPRMT.DESC;
            BPCNRULE.CDESC = BPRPRMT.CDESC;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCNRULE.DATA);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCOCKWD.RC.RC_CODE);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            WS_FOUND_FLG = 'N';
        } else {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZUFLT() throws IOException,SQLException,Exception {
        if (BPCIFLTN.FUNC == 'S') {
            CEP.TRC(SCCGWA, "INIT1");
            BPCUFLT.FUNC = 'S';
        } else if (BPCIFLTN.FUNC == 'C') {
            CEP.TRC(SCCGWA, "INIT2");
            BPCUFLT.FUNC = 'C';
        } else {
            CEP.TRC(SCCGWA, "INIT3");
            BPCUFLT.FUNC = 'I';
        }
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZUFLT, BPCUFLT);
        CEP.TRC(SCCGWA, BPCUFLT);
        CEP.TRC(SCCGWA, BPCUFLT.RC);
        if (BPCUFLT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCUFLT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQBCH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-BCH", BPCPQBCH);
        if (BPCPQBCH.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQBCH.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPGDIN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-GET-DT-INFO", BPCPGDIN);
        CEP.TRC(SCCGWA, BPCPGDIN.RC.RC_CODE);
        if (BPCPGDIN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPGDIN.RC);
            BPCIFLTN.RC.RC_CODE = Short.parseShort(JIBS_tmp_str[0]);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCIFLTN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCIFLTN = ");
            CEP.TRC(SCCGWA, BPCIFLTN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
