package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBVLT {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "BR USE BV INFOMATION MAINTAIN";
    String K_CPY_BPRBVLT = "BPRBVLT";
    String CPN_BR_RIS_BRW = "BP-BR-RIS-BRW";
    String CPN_R_ADW_BVLT = "BP-R-ADW-BVLT";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String CPN_R_STARTBR_BVLT = "BP-R-STARTBR-BVLT";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_R_BRW_BVLT = "BP-R-BRW-BVLT   ";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    BPZSBVLT_WS_BVLT_DETAIL WS_BVLT_DETAIL = new BPZSBVLT_WS_BVLT_DETAIL();
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCOBVLO BPCOBVLO = new BPCOBVLO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCRBVLB BPCRBVLB = new BPCRBVLB();
    BPCRBVLT BPCRBVLT = new BPCRBVLT();
    BPRBVLT BPRBVLT = new BPRBVLT();
    BPRBVLT BPRORIS = new BPRBVLT();
    SCCGWA SCCGWA;
    BPCSBVLT BPCSBVLT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBVLT BPCSBVLT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVLT = BPCSBVLT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVLT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRBVLB);
        IBS.init(SCCGWA, BPCOBVLO);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBVLT.FUNC);
        if (BPCSBVLT.FUNC == 'A') {
            B010_ADD_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSBVLT.FUNC == 'D') {
            B040_QUERY_PROCESS();
            if (pgmRtn) return;
            B020_DELETE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSBVLT.FUNC == 'M') {
            B040_QUERY_PROCESS();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B030_01_HISTORY_RECORD();
            if (pgmRtn) return;
            R010_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSBVLT.FUNC == 'I') {
            B040_QUERY_PROCESS();
            if (pgmRtn) return;
            R010_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSBVLT.FUNC == 'B') {
            B050_QUERY_DATA_PROCESS();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "ERR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_ADD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBVLT);
        B010_CHECK_BR_EXIST();
        if (pgmRtn) return;
        R000_TRANS_DATA_PARAMETER();
        if (pgmRtn) return;
        BPCRBVLT.POINTER = BPRBVLT;
        BPCRBVLT.LEN = 106;
        BPCRBVLT.INFO.FUNC = 'A';
        S000_CALL_BPZRBVLT();
        if (pgmRtn) return;
        if (BPCRBVLT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBVLT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        B010_01_HISTORY_RECORD();
        if (pgmRtn) return;
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
    }
    public void B010_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBVLT;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_BR_EXIST() throws IOException,SQLException,Exception {
        if (BPCSBVLT.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVLT.BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
        }
        if (BPCSBVLT.BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSBVLT.FUNC == 'A') {
            IBS.init(SCCGWA, BPRBVLT);
            IBS.init(SCCGWA, BPCRBVLT);
            CEP.TRC(SCCGWA, BPCRBVLB.RETURN_INFO);
            CEP.TRC(SCCGWA, BPCSBVLT.BV_CODE);
            CEP.TRC(SCCGWA, BPCSBVLT.BR);
            BPRBVLT.KEY.BV_CODE = BPCSBVLT.BV_CODE;
            BPRBVLT.KEY.BR = BPCSBVLT.BR;
            CEP.TRC(SCCGWA, BPRBVLT.KEY.BV_CODE);
            CEP.TRC(SCCGWA, BPRBVLT.KEY.BR);
            BPCRBVLT.INFO.FUNC = 'Q';
            BPCRBVLT.POINTER = BPRBVLT;
            BPCRBVLT.LEN = 106;
            S000_CALL_BPZRBVLT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRBVLT);
            CEP.TRC(SCCGWA, BPCRBVLT.RETURN_INFO);
            if (BPCRBVLT.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_LIMIT_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBVLT);
        B010_CHECK_BR_EXIST();
        if (pgmRtn) return;
        BPRBVLT.KEY.BV_CODE = BPCSBVLT.BV_CODE;
        BPRBVLT.KEY.BR = BPCSBVLT.BR;
        BPCRBVLT.POINTER = BPRBVLT;
        BPCRBVLT.LEN = 106;
        BPCRBVLT.INFO.FUNC = 'R';
        S000_CALL_BPZRBVLT();
        if (pgmRtn) return;
        if (BPCRBVLT.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVLT_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPCRBVLT.INFO.FUNC = 'D';
        CEP.TRC(SCCGWA, "DELETE1");
        CEP.TRC(SCCGWA, BPRBVLT);
        BPCRBVLT.POINTER = BPRBVLT;
        BPCRBVLT.LEN = 106;
        BPCRBVLT.INFO.FUNC = 'D';
        S000_CALL_BPZRBVLT();
        if (pgmRtn) return;
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBVLT;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_02_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBVLT;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBVLT);
        B010_CHECK_BR_EXIST();
        if (pgmRtn) return;
        BPRBVLT.KEY.BV_CODE = BPCSBVLT.BV_CODE;
        BPRBVLT.KEY.BR = BPCSBVLT.BR;
        CEP.TRC(SCCGWA, BPRBVLT.KEY.BV_CODE);
        CEP.TRC(SCCGWA, BPRBVLT.KEY.BR);
        BPCRBVLT.POINTER = BPRBVLT;
        BPCRBVLT.LEN = 106;
        BPCRBVLT.INFO.FUNC = 'R';
        S000_CALL_BPZRBVLT();
        if (pgmRtn) return;
        if (BPCRBVLT.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVLT_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        BPRBVLT.LMT_U_RL = BPCSBVLT.LMT_U_RL;
        BPRBVLT.LMT_L_RL = BPCSBVLT.LMT_L_RL;
        BPRBVLT.LMT_U_PL = BPCSBVLT.LMT_U_PL;
        BPRBVLT.LMT_L_PL = BPCSBVLT.LMT_L_PL;
        BPRBVLT.LMT_U_BL = BPCSBVLT.LMT_U_BL;
        BPRBVLT.LMT_L_BL = BPCSBVLT.LMT_L_BL;
        BPRBVLT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBVLT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCRBVLT.POINTER = BPRBVLT;
        BPCRBVLT.LEN = 106;
        BPCRBVLT.INFO.FUNC = 'U';
        S000_CALL_BPZRBVLT();
        if (pgmRtn) return;
        if (BPCRBVLT.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRBVLT.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "HISTORYSTART");
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBVLT;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.OLD_DAT_PT = BPRORIS;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRBVLT;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.FMT_ID);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "HISTORYEND");
    }
    public void B040_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBVLT);
        B010_CHECK_BR_EXIST();
        if (pgmRtn) return;
        BPRBVLT.KEY.BV_CODE = BPCSBVLT.BV_CODE;
        BPRBVLT.KEY.BR = BPCSBVLT.BR;
        BPCRBVLT.POINTER = BPRBVLT;
        BPCRBVLT.LEN = 106;
        BPCRBVLT.INFO.FUNC = 'Q';
        S000_CALL_BPZRBVLT();
        if (pgmRtn) return;
        if (BPCRBVLT.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVLT_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
    }
    public void B050_QUERY_DATA_PROCESS() throws IOException,SQLException,Exception {
        B051_STARTBR_PROCESS();
        if (pgmRtn) return;
        B052_READNEXT_PROCESS();
        if (pgmRtn) return;
        WS_CNT = 0;
        while (WS_TBL_FLAG != 'N' 
            && WS_CNT <= 1000 
            && SCCMPAG.FUNC != 'E') {
            WS_CNT = WS_CNT + 1;
            if (WS_CNT == 1) {
                B050_01_OUT_TITLE();
                if (pgmRtn) return;
            }
            B050_02_OUT_BRW_DATA();
            if (pgmRtn) return;
            B052_READNEXT_PROCESS();
            if (pgmRtn) return;
        }
        B053_ENDBR_PROCESS();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSBVLT.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOBVLO;
        SCCFMT.DATA_LEN = 312;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, BPCOBVLO);
    }
    public void R010_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSBVLT.OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_BVLT_DETAIL;
        SCCFMT.DATA_LEN = 311;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRBVLT.KEY.BV_CODE = BPCSBVLT.BV_CODE;
        BPRBVLT.KEY.BR = BPCSBVLT.BR;
        BPRBVLT.LMT_U_RL = BPCSBVLT.LMT_U_RL;
        BPRBVLT.LMT_L_RL = BPCSBVLT.LMT_L_RL;
        BPRBVLT.LMT_U_PL = BPCSBVLT.LMT_U_PL;
        BPRBVLT.LMT_L_PL = BPCSBVLT.LMT_L_PL;
        BPRBVLT.LMT_U_BL = BPCSBVLT.LMT_U_BL;
        BPRBVLT.LMT_L_BL = BPCSBVLT.LMT_L_BL;
        BPRBVLT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBVLT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOBVLO.BV_CODE = "" + BPCSBVLT.FUNC;
        JIBS_tmp_int = BPCOBVLO.BV_CODE.length();
        for (int i=0;i<1-JIBS_tmp_int;i++) BPCOBVLO.BV_CODE = "0" + BPCOBVLO.BV_CODE;
        BPCOBVLO.BV_CODE = BPRBVLT.KEY.BV_CODE;
        BPCOBVLO.BR = BPRBVLT.KEY.BR;
        BPCOBVLO.LMT_U_RL = BPRBVLT.LMT_U_RL;
        BPCOBVLO.LMT_L_RL = BPRBVLT.LMT_L_RL;
        BPCOBVLO.LMT_U_PL = BPRBVLT.LMT_U_PL;
        BPCOBVLO.LMT_L_PL = BPRBVLT.LMT_L_PL;
        BPCOBVLO.LMT_U_BL = BPRBVLT.LMT_U_BL;
        BPCOBVLO.LMT_L_BL = BPRBVLT.LMT_L_BL;
        BPCOBVLO.BV_CNM = BPCFBVQU.TX_DATA.BV_CNM;
        BPCOBVLO.BV_ENM = BPCFBVQU.TX_DATA.BV_ENM;
        BPCOBVLO.UPD_DATE = BPRBVLT.UPD_DATE;
        BPCOBVLO.UPD_TLR = BPRBVLT.UPD_TLR;
        BPCOBVLO.CRT_DATE = BPRBVLT.CRT_DATE;
        BPCOBVLO.CRT_TLR = BPRBVLT.CRT_TLR;
    }
    public void B051_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBVLT);
        BPRBVLT.KEY.BV_CODE = BPCSBVLT.BV_CODE;
        BPRBVLT.KEY.BR = BPCSBVLT.BR;
        BPCRBVLB.INFO.FUNC = 'B';
        BPCRBVLB.POINTER = BPRBVLT;
        BPCRBVLB.LEN = 106;
        S000_CALL_BPZRBVLB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRBVLT.KEY.BR);
    }
    public void B052_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCRBVLB.INFO.FUNC = 'N';
        BPCRBVLB.POINTER = BPRBVLT;
        BPCRBVLB.LEN = 106;
        S000_CALL_BPZRBVLB();
        if (pgmRtn) return;
        if (BPCRBVLB.RETURN_INFO == 'F') {
            WS_TBL_FLAG = 'Y';
        } else if (BPCRBVLB.RETURN_INFO == 'N') {
            WS_TBL_FLAG = 'N';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B053_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCRBVLB.INFO.FUNC = 'E';
        BPCRBVLB.LEN = 106;
        BPCRBVLB.POINTER = BPRBVLT;
        S000_CALL_BPZRBVLB();
        if (pgmRtn) return;
        if (BPCRBVLB.RETURN_INFO != 'F') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 312;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOBVLO);
        BPCOBVLO.BV_CODE = BPRBVLT.KEY.BV_CODE;
        BPCOBVLO.BR = BPRBVLT.KEY.BR;
        BPCOBVLO.LMT_U_RL = BPRBVLT.LMT_U_RL;
        BPCOBVLO.LMT_L_RL = BPRBVLT.LMT_L_RL;
        BPCOBVLO.LMT_U_PL = BPRBVLT.LMT_U_PL;
        BPCOBVLO.LMT_L_PL = BPRBVLT.LMT_L_PL;
        BPCOBVLO.LMT_U_BL = BPRBVLT.LMT_U_BL;
        BPCOBVLO.LMT_L_BL = BPRBVLT.LMT_L_BL;
        BPCOBVLO.BV_CNM = BPCFBVQU.TX_DATA.BV_CNM;
        BPCOBVLO.BV_ENM = BPCFBVQU.TX_DATA.BV_ENM;
        BPCOBVLO.UPD_DATE = BPRBVLT.UPD_DATE;
        BPCOBVLO.UPD_TLR = BPRBVLT.UPD_TLR;
        BPCOBVLO.CRT_DATE = BPRBVLT.CRT_DATE;
        BPCOBVLO.CRT_TLR = BPRBVLT.CRT_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOBVLO);
        SCCMPAG.DATA_LEN = 312;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRBVLT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_BVLT, BPCRBVLT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRBVLB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_BVLT, BPCRBVLB);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
