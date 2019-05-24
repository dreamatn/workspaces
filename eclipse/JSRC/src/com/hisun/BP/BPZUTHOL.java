package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPZUTHOL {
    boolean pgmRtn = false;
    String CPN_R_MAINTAIN_THOL = "BP-R-MAINTAIN-THOL";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_HIS_REMARKS = "COUNTRY HOLIDAY INFORNATION MAINTAIN";
    String K_CPY_BPRTHOL = "BPRTHOL";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_J = 0;
    short WS_K = 0;
    short WS_L = 0;
    String WS_HOL_DATA = " ";
    BPZUTHOL_REDEFINES7 REDEFINES7 = new BPZUTHOL_REDEFINES7();
    String WS_HOL_DATA_NEW = " ";
    BPZUTHOL_REDEFINES15 REDEFINES15 = new BPZUTHOL_REDEFINES15();
    String WS_HOL_DATA_OLD = " ";
    BPZUTHOL_REDEFINES23 REDEFINES23 = new BPZUTHOL_REDEFINES23();
    char WS_DATA_CHANG_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPRTHOL BPRTHOL = new BPRTHOL();
    BPVTHOL BPVNTHOL = new BPVTHOL();
    BPVTHOL BPVOTHOL = new BPVTHOL();
    BPCRTHOL BPCRTHOL = new BPCRTHOL();
    SCCGWA SCCGWA;
    BPCSTHOL BPCUTHOL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSTHOL BPCUTHOL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCUTHOL = BPCUTHOL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUTHOL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCPNHIS);
        IBS.init(SCCGWA, BPVNTHOL);
        IBS.init(SCCGWA, BPVOTHOL);
        IBS.init(SCCGWA, BPCRTHOL);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUTHOL.FUNC);
        if (BPCUTHOL.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUTHOL.FUNC == 'A') {
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUTHOL.FUNC == 'U') {
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUTHOL.FUNC == 'D') {
            B040_DELETE_PROCESS();
            if (pgmRtn) return;
        } else if (BPCUTHOL.FUNC == 'B') {
            B050_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + BPCUTHOL.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHOL);
        IBS.init(SCCGWA, BPCRTHOL);
        BPRTHOL.KEY.CAL_CD = BPCUTHOL.KEY.CAL_CD;
        BPRTHOL.KEY.EFF_DATE = BPCUTHOL.KEY.EFF_DT;
        BPRTHOL.BLOB_HOL_TXT = IBS.CLS2CPY(SCCGWA, BPRTHOL.REDEFINES7);
        BPCRTHOL.DATA_LEN = 54;
        CEP.TRC(SCCGWA, BPRTHOL.REDEFINES7.HOL_TXT_LEN1);
        CEP.TRC(SCCGWA, BPCRTHOL.DATA_LEN);
        BPCRTHOL.POINTER = BPRTHOL;
        BPCRTHOL.FUNC = 'Q';
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
        if (BPCRTHOL.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTY_CITY_HOL_NOTFND, BPCUTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        B020_01_CHECK_PROCESS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRTHOL);
        IBS.init(SCCGWA, BPCRTHOL);
        R010_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCRTHOL.DATA_LEN = 54;
        BPCRTHOL.POINTER = BPRTHOL;
        BPCRTHOL.FUNC = 'A';
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
        if (BPCRTHOL.RETURN_INFO == 'D') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCUTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        S000_HISTORY_RECORD();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B020_01_CHECK_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHOL);
        IBS.init(SCCGWA, BPCRTHOL);
        BPRTHOL.KEY.CAL_CD = BPCUTHOL.KEY.CAL_CD;
        BPRTHOL.KEY.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRTHOL.BLOB_HOL_TXT = IBS.CLS2CPY(SCCGWA, BPRTHOL.REDEFINES7);
        BPCRTHOL.DATA_LEN = 54;
        BPCRTHOL.FUNC = 'B';
        BPCRTHOL.OPT = 'S';
        BPCRTHOL.POINTER = BPRTHOL;
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
        BPCRTHOL.OPT = 'N';
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
        if (BPCRTHOL.RETURN_INFO == 'F') {
            BPCRTHOL.OPT = 'E';
            S000_CALL_BPZRTHOL();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FUTURE_RECORD_EXIST, BPCUTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRTHOL.OPT = 'E';
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHOL);
        IBS.init(SCCGWA, BPCRTHOL);
        IBS.init(SCCGWA, BPVNTHOL);
        IBS.init(SCCGWA, BPVOTHOL);
        WS_DATA_CHANG_FLAG = 'N';
        R020_TRANS_DATA_NTHOL();
        if (pgmRtn) return;
        BPRTHOL.KEY.CAL_CD = BPCUTHOL.KEY.CAL_CD;
        BPRTHOL.KEY.EFF_DATE = BPCUTHOL.KEY.EFF_DT;
        BPRTHOL.BLOB_HOL_TXT = IBS.CLS2CPY(SCCGWA, BPRTHOL.REDEFINES7);
        BPCRTHOL.DATA_LEN = 54;
        BPCRTHOL.POINTER = BPRTHOL;
        BPCRTHOL.FUNC = 'R';
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
        if (BPCRTHOL.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTY_CITY_HOL_NOTFND, BPCUTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R030_TRANS_DATA_OTHOL();
        if (pgmRtn) return;
        R040_TRANS_OPT_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_HOL_DATA_NEW);
        CEP.TRC(SCCGWA, WS_HOL_DATA_OLD);
        if (WS_HOL_DATA_NEW.equalsIgnoreCase(WS_HOL_DATA_OLD)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_UPD_DATA_NOT_CHG, BPCUTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRTHOL);
        IBS.init(SCCGWA, BPCRTHOL);
        R010_INPUT_DATA_PROCESS();
        if (pgmRtn) return;
        BPCRTHOL.DATA_LEN = 54;
        BPCRTHOL.POINTER = BPRTHOL;
        BPCRTHOL.FUNC = 'U';
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
        S000_HISTORY_RECORD();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B040_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHOL);
        IBS.init(SCCGWA, BPCRTHOL);
        BPRTHOL.KEY.CAL_CD = BPCUTHOL.KEY.CAL_CD;
        BPRTHOL.KEY.EFF_DATE = BPCUTHOL.KEY.EFF_DT;
        BPRTHOL.BLOB_HOL_TXT = IBS.CLS2CPY(SCCGWA, BPRTHOL.REDEFINES7);
        BPCRTHOL.DATA_LEN = 54;
        BPCRTHOL.POINTER = BPRTHOL;
        BPCRTHOL.FUNC = 'R';
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
        if (BPCRTHOL.RETURN_INFO == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CNTY_CITY_HOL_NOTFND, BPCUTHOL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCRTHOL.FUNC = 'D';
        S000_CALL_BPZRTHOL();
        if (pgmRtn) return;
        S000_HISTORY_RECORD();
        if (pgmRtn) return;
        R000_OUTPUT_DATA_PROCESS();
        if (pgmRtn) return;
    }
    public void B050_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUTHOL.RC);
        IBS.init(SCCGWA, BPRTHOL);
        IBS.init(SCCGWA, BPCRTHOL);
        BPRTHOL.KEY.CAL_CD = BPCUTHOL.KEY.CAL_CD;
        BPRTHOL.KEY.EFF_DATE = BPCUTHOL.KEY.EFF_DT;
        CEP.TRC(SCCGWA, BPRTHOL.KEY.EFF_DATE);
        BPRTHOL.BLOB_HOL_TXT = IBS.CLS2CPY(SCCGWA, BPRTHOL.REDEFINES7);
        BPCRTHOL.DATA_LEN = 54;
        BPCRTHOL.FUNC = 'B';
        BPCRTHOL.POINTER = BPRTHOL;
        CEP.TRC(SCCGWA, BPCUTHOL.FUNC_OPT);
        if (BPCUTHOL.FUNC_OPT == 'S') {
            BPCRTHOL.OPT = 'S';
            S000_CALL_BPZRTHOL();
            if (pgmRtn) return;
            if (BPCRTHOL.RETURN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCUTHOL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
        if (BPCUTHOL.FUNC_OPT == 'N') {
            BPCRTHOL.OPT = 'N';
            S000_CALL_BPZRTHOL();
            if (pgmRtn) return;
            if (BPCRTHOL.RETURN_INFO == 'N') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_NOTFND, BPCUTHOL.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                R000_OUTPUT_DATA_PROCESS();
                if (pgmRtn) return;
            }
        }
        if (BPCUTHOL.FUNC_OPT == 'E') {
            BPCRTHOL.OPT = 'E';
            S000_CALL_BPZRTHOL();
            if (pgmRtn) return;
        }
    }
    public void R000_OUTPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRTHOL.KEY);
        BPCUTHOL.KEY.CAL_CD = BPRTHOL.KEY.CAL_CD;
        BPCUTHOL.KEY.EFF_DT = BPRTHOL.KEY.EFF_DATE;
        WS_HOL_DATA = BPRTHOL.REDEFINES7.HOL_TXT_TEXT1;
        IBS.CPY2CLS(SCCGWA, WS_HOL_DATA, REDEFINES7);
        CEP.TRC(SCCGWA, BPRTHOL.REDEFINES7.HOL_TXT_TEXT1);
        for (WS_I = 1; WS_I <= 50; WS_I += 1) {
            CEP.TRC(SCCGWA, BPCUTHOL.HOL_DATA[WS_I-1].HOL_DT);
            CEP.TRC(SCCGWA, BPCUTHOL.HOL_DATA[WS_I-1].HOL_CNT);
            BPCUTHOL.HOL_DATA[WS_I-1].HOL_DT = REDEFINES7.WS_HOL_DATA1[WS_I-1].WS_HOL_DT;
            BPCUTHOL.HOL_DATA[WS_I-1].HOL_CNT = REDEFINES7.WS_HOL_DATA1[WS_I-1].WS_HOL_CNT;
            BPCUTHOL.HOL_DATA[WS_I-1].HOL_NAME = REDEFINES7.WS_HOL_DATA1[WS_I-1].WS_HOL_NAME;
            BPCUTHOL.HOL_DATA[WS_I-1].HOL_TPY = REDEFINES7.WS_HOL_DATA1[WS_I-1].WS_HOL_TPY;
            BPCUTHOL.HOL_DATA[WS_I-1].HOL_OPT = REDEFINES7.WS_HOL_DATA1[WS_I-1].WS_HOL_OPT;
        }
        BPCUTHOL.LAST_DATE = BPRTHOL.LAST_DATE;
        BPCUTHOL.LAST_TELLER = BPRTHOL.LAST_TELLER;
        BPCUTHOL.TS = BPRTHOL.TS;
    }
    public void R010_INPUT_DATA_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCUTHOL.KEY);
        BPRTHOL.KEY.CAL_CD = BPCUTHOL.KEY.CAL_CD;
        BPRTHOL.KEY.EFF_DATE = BPCUTHOL.KEY.EFF_DT;
        BPRTHOL.LAST_DATE = BPCUTHOL.LAST_DATE;
        BPRTHOL.LAST_TELLER = BPCUTHOL.LAST_TELLER;
        for (WS_I = 1; WS_I <= 50; WS_I += 1) {
            CEP.TRC(SCCGWA, WS_I);
            CEP.TRC(SCCGWA, BPCUTHOL.HOL_DATA[WS_I-1].HOL_DT);
            CEP.TRC(SCCGWA, BPCUTHOL.HOL_DATA[WS_I-1].HOL_NAME);
            CEP.TRC(SCCGWA, BPCUTHOL.HOL_DATA[WS_I-1].HOL_TPY);
            CEP.TRC(SCCGWA, BPCUTHOL.HOL_DATA[WS_I-1].HOL_OPT);
            REDEFINES7.WS_HOL_DATA1[WS_I-1].WS_HOL_DT = BPCUTHOL.HOL_DATA[WS_I-1].HOL_DT;
            WS_HOL_DATA = IBS.CLS2CPY(SCCGWA, REDEFINES7);
            REDEFINES7.WS_HOL_DATA1[WS_I-1].WS_HOL_CNT = BPCUTHOL.HOL_DATA[WS_I-1].HOL_CNT;
            WS_HOL_DATA = IBS.CLS2CPY(SCCGWA, REDEFINES7);
            REDEFINES7.WS_HOL_DATA1[WS_I-1].WS_HOL_NAME = BPCUTHOL.HOL_DATA[WS_I-1].HOL_NAME;
            WS_HOL_DATA = IBS.CLS2CPY(SCCGWA, REDEFINES7);
            REDEFINES7.WS_HOL_DATA1[WS_I-1].WS_HOL_TPY = BPCUTHOL.HOL_DATA[WS_I-1].HOL_TPY;
            WS_HOL_DATA = IBS.CLS2CPY(SCCGWA, REDEFINES7);
            REDEFINES7.WS_HOL_DATA1[WS_I-1].WS_HOL_OPT = BPCUTHOL.HOL_DATA[WS_I-1].HOL_OPT;
            WS_HOL_DATA = IBS.CLS2CPY(SCCGWA, REDEFINES7);
        }
        BPRTHOL.REDEFINES7.HOL_TXT_TEXT1 = WS_HOL_DATA;
        IBS.CPY2CLS(SCCGWA, BPRTHOL.REDEFINES7.HOL_TXT_TEXT1, BPRTHOL.REDEFINES7.REDEFINES9);
