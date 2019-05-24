package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.SM.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSMTXI {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_TXIF = "BP-R-MGM-TXIF       ";
    String CPN_R_TXIB = "BP-R-MGM-TXIB       ";
    String CPN_R_PRMR = "BP-PARM-READ        ";
    String CPN_F_WORKFLOW = "SM-S-INQ-WFW        ";
    String K_PARM_TYPE = "TRT  ";
    String K_OUTPUT_FMT_X = "BPX01";
    String K_OUTPUT_FMT_9 = "BP501";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 99;
    int K_COL_CNT = 18;
    String K_HIS_REMARK = "TXN INFOMATION MAINTAIN";
    String K_HIS_COPYBOOK = "BPRTXIF ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRTXIF BPRTXIF = new BPRTXIF();
    BPRTXIF BPROTXIF = new BPRTXIF();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPCRTXIF BPCRTXIF = new BPCRTXIF();
    BPCRTXIB BPCRTXIB = new BPCRTXIB();
    BPCOTXI1 BPCOTXI1 = new BPCOTXI1();
    BPCOTXIL BPCOTXIL = new BPCOTXIL();
    SMCSQWFW SMCSQWFW = new SMCSQWFW();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSMTXI BPCSMTXI;
    public void MP(SCCGWA SCCGWA, BPCSMTXI BPCSMTXI) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMTXI = BPCSMTXI;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMTXI return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRTXIF);
        IBS.init(SCCGWA, BPROTXIF);
        IBS.init(SCCGWA, BPCRTXIF);
        IBS.init(SCCGWA, BPCRTXIB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCSMTXI.FUNC == 'Q') {
            B010_QUERY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMTXI.FUNC == 'A') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B020_CREATE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMTXI.FUNC == 'U') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMTXI.FUNC == 'D') {
            B001_CHECK_INPUT();
            if (pgmRtn) return;
            B050_DELETE_PROCESS();
            if (pgmRtn) return;
            B080_HISTORY_PROCESS();
            if (pgmRtn) return;
            B090_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSMTXI.FUNC == '1'
            || BPCSMTXI.FUNC == '2') {
            B040_BROWSER_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B001_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCSMTXI.IN_FLG == 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_OPTION_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMTXI.ATH_LVL1 != '0' 
            && BPCSMTXI.ATH_LVL2 != '0' 
            && BPCSMTXI.ATH_LVL1 > BPCSMTXI.ATH_LVL2) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_LVL12;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMTXI.ATH_LVL1 == '0' 
            && BPCSMTXI.ATH_LVL2 != '0') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_LVL12;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMTXI.STSW == null) BPCSMTXI.STSW = "";
        JIBS_tmp_int = BPCSMTXI.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCSMTXI.STSW += " ";
        if (BPCSMTXI.STSW.substring(0, 1).equalsIgnoreCase("1")) {
            if (BPCSMTXI.STSW == null) BPCSMTXI.STSW = "";
            JIBS_tmp_int = BPCSMTXI.STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSMTXI.STSW += " ";
            if (BPCSMTXI.STSW == null) BPCSMTXI.STSW = "";
            JIBS_tmp_int = BPCSMTXI.STSW.length();
            for (int i=0;i<64-JIBS_tmp_int;i++) BPCSMTXI.STSW += " ";
            if (BPCSMTXI.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1") 
                || BPCSMTXI.STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase("1")) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ERR_MNT_PRIV_STS;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTXIF);
        BPRTXIF.KEY.IN_FLG = BPCSMTXI.IN_FLG;
        BPRTXIF.KEY.SYS_MMO = BPCSMTXI.SYS_MMO;
        BPRTXIF.KEY.TX_CD = BPCSMTXI.TX_CD;
        BPCRTXIF.INFO.FUNC = 'Q';
        S000_CALL_BPZRTXIF();
        if (pgmRtn) return;
        if (BPCRTXIF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXIF_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_CREATE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTXIF);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRTXIF.INFO.FUNC = 'A';
        S000_CALL_BPZRTXIF();
        if (pgmRtn) return;
        if (BPCRTXIF.RETURN_INFO == 'D') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXIF_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTXIF);
        BPCRTXIF.INFO.FUNC = 'R';
        BPRTXIF.KEY.IN_FLG = BPCSMTXI.IN_FLG;
        BPRTXIF.KEY.SYS_MMO = BPCSMTXI.SYS_MMO;
        BPRTXIF.KEY.TX_CD = BPCSMTXI.TX_CD;
        S000_CALL_BPZRTXIF();
        if (pgmRtn) return;
        if (BPCRTXIF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXIF_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTXIF, BPROTXIF);
        R000_TRANS_DATA();
        if (pgmRtn) return;
        BPCRTXIF.INFO.FUNC = 'U';
        S000_CALL_BPZRTXIF();
        if (pgmRtn) return;
    }
    public void B050_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTXIF);
        BPCRTXIF.INFO.FUNC = 'R';
        BPRTXIF.KEY.IN_FLG = BPCSMTXI.IN_FLG;
        BPRTXIF.KEY.SYS_MMO = BPCSMTXI.SYS_MMO;
        BPRTXIF.KEY.TX_CD = BPCSMTXI.TX_CD;
        S000_CALL_BPZRTXIF();
        if (pgmRtn) return;
        if (BPCRTXIF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TXIF_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.CLONE(SCCGWA, BPRTXIF, BPROTXIF);
        BPCRTXIF.INFO.FUNC = 'D';
        S000_CALL_BPZRTXIF();
        if (pgmRtn) return;
    }
    public void B040_BROWSER_PROCESS() throws IOException,SQLException,Exception {
        if (BPCSMTXI.FUNC == '1') {
            B040_01_BRW_VIEW();
            if (pgmRtn) return;
        } else if (BPCSMTXI.FUNC == '2') {
            B040_02_BRW_OTHER();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B040_01_BRW_VIEW() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTXIF);
        if (BPCSMTXI.IN_FLG == ' ') {
            BPRTXIF.KEY.IN_FLG = ALL.charAt(0);
        } else {
            BPRTXIF.KEY.IN_FLG = BPCSMTXI.IN_FLG;
        }
        if (BPCSMTXI.SYS_MMO.trim().length() == 0) {
            BPRTXIF.KEY.SYS_MMO = "%%%%%";
        } else {
            BPRTXIF.KEY.SYS_MMO = BPCSMTXI.SYS_MMO;
        }
        if (BPCSMTXI.TX_CD.trim().length() == 0) {
            BPRTXIF.KEY.TX_CD = "%%%%%%%";
        } else {
            BPRTXIF.KEY.TX_CD = BPCSMTXI.TX_CD;
        }
        BPCRTXIB.INFO.FUNC = '1';
        S000_CALL_BPZRTXIB();
        if (pgmRtn) return;
        BPCRTXIB.INFO.FUNC = 'N';
        S000_CALL_BPZRTXIB();
        if (pgmRtn) return;
        if (BPCRTXIB.RETURN_INFO == 'F') {
            B040_01_01_OUT_TITLE();
            if (pgmRtn) return;
        }
        WS_CNT = 0;
        while (BPCRTXIB.RETURN_INFO != 'N' 
            && WS_CNT <= 1000 
            && SCCMPAG.FUNC != 'E') {
            B040_01_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            WS_CNT += 1;
            BPCRTXIB.INFO.FUNC = 'N';
            S000_CALL_BPZRTXIB();
            if (pgmRtn) return;
        }
        BPCRTXIB.INFO.FUNC = 'E';
        S000_CALL_BPZRTXIB();
        if (pgmRtn) return;
    }
    public void B040_01_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 47;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = (short) K_COL_CNT;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_01_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTXIL);
        BPCOTXIL.IN_FLG = BPRTXIF.KEY.IN_FLG;
        BPCOTXIL.SYS_MMO = BPRTXIF.KEY.SYS_MMO;
        BPCOTXIL.TX_CD = BPRTXIF.KEY.TX_CD;
        BPCOTXIL.STS = BPRTXIF.STS;
        BPCOTXIL.TX_LVL = BPRTXIF.TX_LVL;
        BPCOTXIL.ATH_LVL1 = BPRTXIF.ATH_LVL1;
        BPCOTXIL.ATH_LVL2 = BPRTXIF.ATH_LVL2;
        BPCOTXIL.SELF_FLG = BPRTXIF.SELF_ATH_FLG;
        BPCOTXIL.COR_FLG = BPRTXIF.COR_ALW_FLG;
        BPCOTXIL.BAT_FLG = BPRTXIF.BAT_ATH_FLG;
        BPCOTXIL.SATH_FLG = BPRTXIF.SATH_FLG;
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        BPCOTXIL.STSW1 = BPRTXIF.STSW.substring(0, 1).charAt(0);
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        BPCOTXIL.STSW2 = BPRTXIF.STSW.substring(2 - 1, 2 + 1 - 1).charAt(0);
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        BPCOTXIL.STSW3 = BPRTXIF.STSW.substring(3 - 1, 3 + 1 - 1).charAt(0);
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        BPCOTXIL.STSW4 = BPRTXIF.STSW.substring(4 - 1, 4 + 1 - 1).charAt(0);
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        BPCOTXIL.STSW5 = BPRTXIF.STSW.substring(5 - 1, 5 + 1 - 1).charAt(0);
        BPCOTXIL.RUN_MODE = BPRTXIF.RUN_MODE;
        BPCOTXIL.CTRL_NO = BPRTXIF.TAB_CD;
        BPCOTXIL.UPD_DT = BPRTXIF.UPD_DT;
        BPCOTXIL.UPD_TLR = BPRTXIF.UPD_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTXIL);
        SCCMPAG.DATA_LEN = 47;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B040_02_BRW_OTHER() throws IOException,SQLException,Exception {
    }
    public void B080_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.OLD_DAT_PT = null;
        BPCPNHIS.INFO.NEW_DAT_PT = null;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARK;
        BPCPNHIS.INFO.FMT_ID = K_HIS_COPYBOOK;
        if (BPCSMTXI.FUNC == 'A') {
            BPCPNHIS.INFO.TX_TYP = 'A';
            BPCPNHIS.INFO.NEW_DAT_PT = BPRTXIF;
        }
        if (BPCSMTXI.FUNC == 'U') {
            BPCPNHIS.INFO.TX_TYP = 'M';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROTXIF;
            BPCPNHIS.INFO.NEW_DAT_PT = BPRTXIF;
        }
        if (BPCSMTXI.FUNC == 'D') {
            BPCPNHIS.INFO.TX_TYP = 'D';
            BPCPNHIS.INFO.OLD_DAT_PT = BPROTXIF;
        }
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B090_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOTXI1);
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_X;
        if (BPCSMTXI.FUNC == 'A' 
            || BPCSMTXI.FUNC == 'U' 
            || BPCSMTXI.FUNC == 'D') {
            SCCFMT.FMTID = K_OUTPUT_FMT_9;
        }
        SCCFMT.DATA_PTR = BPCOTXI1;
        SCCFMT.DATA_LEN = 47;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA() throws IOException,SQLException,Exception {
        BPRTXIF.KEY.IN_FLG = BPCSMTXI.IN_FLG;
        BPRTXIF.KEY.SYS_MMO = BPCSMTXI.SYS_MMO;
        BPRTXIF.KEY.TX_CD = BPCSMTXI.TX_CD;
        BPRTXIF.STS = BPCSMTXI.STS;
        BPRTXIF.TX_LVL = BPCSMTXI.TX_LVL;
        BPRTXIF.ATH_LVL1 = BPCSMTXI.ATH_LVL1;
        BPRTXIF.ATH_LVL2 = BPCSMTXI.ATH_LVL2;
        BPRTXIF.SELF_ATH_FLG = BPCSMTXI.SELF_ATH_FLG;
        BPRTXIF.COR_ALW_FLG = BPCSMTXI.COR_ALW_FLG;
        BPRTXIF.BAT_ATH_FLG = BPCSMTXI.BAT_ATH_FLG;
        BPRTXIF.SATH_FLG = BPCSMTXI.SATH_FLG;
        BPRTXIF.STSW = BPCSMTXI.STSW;
        BPRTXIF.RUN_MODE = BPCSMTXI.RUN_MODE;
        BPRTXIF.TAB_CD = BPCSMTXI.TAB_CD;
        CEP.TRC(SCCGWA, BPRTXIF.TAB_CD);
        BPRTXIF.UPD_DT = BPCSMTXI.UPD_DT;
        BPRTXIF.UPD_TLR = BPCSMTXI.UPD_TLR;
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOTXI1.IN_FLG = BPRTXIF.KEY.IN_FLG;
        BPCOTXI1.SYS_MMO = BPRTXIF.KEY.SYS_MMO;
        BPCOTXI1.TX_CD = BPRTXIF.KEY.TX_CD;
        BPCOTXI1.STS = BPRTXIF.STS;
        BPCOTXI1.TX_LVL = BPRTXIF.TX_LVL;
        BPCOTXI1.ATH_LVL1 = BPRTXIF.ATH_LVL1;
        BPCOTXI1.ATH_LVL2 = BPRTXIF.ATH_LVL2;
        BPCOTXI1.SELF_ATH_FLG = BPRTXIF.SELF_ATH_FLG;
        BPCOTXI1.COR_ALW_FLG = BPRTXIF.COR_ALW_FLG;
        BPCOTXI1.BAT_ATH_FLG = BPRTXIF.BAT_ATH_FLG;
        BPCOTXI1.SATH_FLG = BPRTXIF.SATH_FLG;
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        BPCOTXI1.STSW1 = BPRTXIF.STSW.substring(0, 1).charAt(0);
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        BPCOTXI1.STSW2 = BPRTXIF.STSW.substring(2 - 1, 2 + 1 - 1).charAt(0);
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        BPCOTXI1.STSW3 = BPRTXIF.STSW.substring(3 - 1, 3 + 1 - 1).charAt(0);
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        BPCOTXI1.STSW4 = BPRTXIF.STSW.substring(4 - 1, 4 + 1 - 1).charAt(0);
        if (BPRTXIF.STSW == null) BPRTXIF.STSW = "";
        JIBS_tmp_int = BPRTXIF.STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPRTXIF.STSW += " ";
        BPCOTXI1.STSW5 = BPRTXIF.STSW.substring(5 - 1, 5 + 1 - 1).charAt(0);
        BPCOTXI1.RUN_MODE = BPRTXIF.RUN_MODE;
        BPCOTXI1.TAB_CD = BPRTXIF.TAB_CD;
        BPCOTXI1.UPD_DT = BPRTXIF.UPD_DT;
        BPCOTXI1.UPD_TLR = BPRTXIF.UPD_TLR;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, CPN_R_PRMR, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_SMZSQWFW() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_WORKFLOW, SMCSQWFW);
        if (SMCSQWFW.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, SMCSQWFW.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTXIF() throws IOException,SQLException,Exception {
        BPCRTXIF.INFO.POINTER = BPRTXIF;
        BPCRTXIF.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, CPN_R_TXIF, BPCRTXIF);
        if (BPCRTXIF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTXIF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRTXIB() throws IOException,SQLException,Exception {
        BPCRTXIB.INFO.POINTER = BPRTXIF;
        BPCRTXIB.INFO.LEN = 106;
        IBS.CALLCPN(SCCGWA, CPN_R_TXIB, BPCRTXIB);
        if (BPCRTXIB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRTXIB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
