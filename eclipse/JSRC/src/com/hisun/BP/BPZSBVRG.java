package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSBVRG {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_HIS_REMARKS = "BR USE BV INFOMATION MAINTAIN";
    String K_CPY_BPRBVRG = "BPRBVRG";
    String CPN_BR_RIS_BRW = "BP-BR-RIS-BRW";
    String CPN_R_ADW_BVRG = "BP-R-ADW-BVRG";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String CPN_R_STARTBR_BVRG = "BP-R-STARTBR-BVRG";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_R_BRW_BVRG = "BP-R-BRW-BVRG   ";
    String WS_ERR_MSG = " ";
    short WS_IDX = 0;
    int WS_I = 0;
    int WS_J = 0;
    int WS_K = 0;
    int WS_CNT = 0;
    int WS_CNT1 = 0;
    BPZSBVRG_WS_BVRG_DETAIL WS_BVRG_DETAIL = new BPZSBVRG_WS_BVRG_DETAIL();
    char WS_TBL_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCTBVRG BPCTBVRG = new BPCTBVRG();
    BPCOBVRO BPCOBVRO = new BPCOBVRO();
    BPCOBRGO BPCOBRGO = new BPCOBRGO();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCRBVRB BPCRBVRB = new BPCRBVRB();
    BPCOBVGO BPCOBVGO = new BPCOBVGO();
    BPRBVRG BPRBVRG = new BPRBVRG();
    BPRBVRG BPRORIS = new BPRBVRG();
    SCCGWA SCCGWA;
    BPCSBVRG BPCSBVRG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSBVRG BPCSBVRG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVRG = BPCSBVRG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVRG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRBVRB);
        IBS.init(SCCGWA, BPCTBVRG);
        IBS.init(SCCGWA, BPCOBVRO);
        IBS.init(SCCGWA, BPCOBRGO);
        IBS.init(SCCGWA, BPCSBVRG.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSBVRG.FUNC);
        if (BPCSBVRG.FUNC == 'A') {
            B010_ADD_PROCESS();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSBVRG.FUNC == 'D') {
            B040_QUERY_PROCESS();
            if (pgmRtn) return;
            B020_DELETE_PROCESS();
            if (pgmRtn) return;
            B020_01_HISTORY_RECORD();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSBVRG.FUNC == 'M') {
            B040_QUERY_PROCESS();
            if (pgmRtn) return;
            B020_DELETE_PROCESS();
            if (pgmRtn) return;
            B030_MODIFY_PROCESS();
            if (pgmRtn) return;
            B030_01_HISTORY_RECORD();
            if (pgmRtn) return;
            R010_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
            R000_TRANS_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSBVRG.FUNC == 'I') {
            B040_QUERY_PROCESS();
            if (pgmRtn) return;
            R010_DATA_OUTPUT();
            if (pgmRtn) return;
        } else if (BPCSBVRG.FUNC == 'B') {
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
        B010_CHECK_BR_EXIST();
        if (pgmRtn) return;
        for (WS_J = 1; WS_J <= 30 
            && BPCSBVRG.BR_INFO[WS_J-1].BR != 0; WS_J += 1) {
            IBS.init(SCCGWA, BPRBVRG);
            R000_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            BPCTBVRG.POINTER = BPRBVRG;
            BPCTBVRG.LEN = 300;
            BPCTBVRG.INFO.FUNC = 'A';
            S000_CALL_BPZRBVRG();
            if (pgmRtn) return;
            if (BPCTBVRG.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTBVRG.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        B010_01_HISTORY_RECORD();
        if (pgmRtn) return;
        R010_TRANS_DATA_OUPUT();
        if (pgmRtn) return;
    }
    public void B010_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBVRG;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_BR_EXIST() throws IOException,SQLException,Exception {
        if (BPCSBVRG.BV_CODE.trim().length() == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVCD_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVRG.BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
        }
        if (BPCSBVRG.BR_INFO[1-1].BR == 0) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSBVRG.FUNC == 'A') {
            IBS.init(SCCGWA, BPRBVRG);
            IBS.init(SCCGWA, BPCRBVRB);
            CEP.TRC(SCCGWA, BPCRBVRB.RETURN_INFO);
            BPRBVRG.KEY.BV_CODE = BPCSBVRG.BV_CODE;
            BPRBVRG.KEY.BR = 0;
            BPCRBVRB.INFO.FUNC = 'F';
            BPCRBVRB.POINTER = BPRBVRG;
            BPCRBVRB.LEN = 300;
            S000_CALL_BPZRBVRB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRBVRG);
            CEP.TRC(SCCGWA, BPCRBVRB.RETURN_INFO);
            if (BPCRBVRB.RETURN_INFO == 'F') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_RANGE_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_DELETE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVRG.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        for (WS_K = 1; WS_K <= 30 
            && WS_BVRG_DETAIL.SDNCS_BR_INFO[WS_K-1].WS_BR != 0; WS_K += 1) {
            IBS.init(SCCGWA, BPRBVRG);
            BPRBVRG.KEY.BV_CODE = WS_BVRG_DETAIL.WS_BV_CODE;
            BPRBVRG.KEY.BR = WS_BVRG_DETAIL.SDNCS_BR_INFO[WS_K-1].WS_BR;
            CEP.TRC(SCCGWA, WS_BVRG_DETAIL.WS_BV_CODE);
            CEP.TRC(SCCGWA, WS_K);
            CEP.TRC(SCCGWA, WS_BVRG_DETAIL.SDNCS_BR_INFO[WS_K-1].WS_BR);
            BPCTBVRG.POINTER = BPRBVRG;
            BPCTBVRG.LEN = 300;
            BPCTBVRG.INFO.FUNC = 'R';
            S000_CALL_BPZRBVRG();
            if (pgmRtn) return;
            if (BPCTBVRG.RETURN_INFO == 'N') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BVRG_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCTBVRG.INFO.FUNC = 'D';
            CEP.TRC(SCCGWA, "DELETE1");
            CEP.TRC(SCCGWA, BPRBVRG);
            BPCTBVRG.POINTER = BPRBVRG;
            BPCTBVRG.LEN = 300;
            BPCTBVRG.INFO.FUNC = 'D';
            S000_CALL_BPZRBVRG();
            if (pgmRtn) return;
            R010_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
        }
    }
    public void B020_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBVRG;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B020_02_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBVRG;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_BR_EXIST();
        if (pgmRtn) return;
        for (WS_J = 1; WS_J <= 30 
            && BPCSBVRG.BR_INFO[WS_J-1].BR != 0; WS_J += 1) {
            IBS.init(SCCGWA, BPRBVRG);
            BPRBVRG.KEY.BV_CODE = BPCSBVRG.BV_CODE;
            BPRBVRG.KEY.BR = BPCSBVRG.BR_INFO[WS_J-1].BR;
            BPRBVRG.BV_CNM = BPCFBVQU.TX_DATA.BV_CNM;
            BPRBVRG.BV_ENM = BPCFBVQU.TX_DATA.BV_ENM;
            BPRBVRG.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPRBVRG.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPRBVRG.CRT_DATE = WS_BVRG_DETAIL.WS_CRT_DATE;
            BPRBVRG.CRT_TLR = WS_BVRG_DETAIL.WS_CRT_TLR;
            BPCTBVRG.POINTER = BPRBVRG;
            BPCTBVRG.LEN = 300;
            BPCTBVRG.INFO.FUNC = 'A';
            S000_CALL_BPZRBVRG();
            if (pgmRtn) return;
            if (BPCTBVRG.RC.RC_CODE != 0) {
                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCTBVRG.RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_01_HISTORY_RECORD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "HISTORYSTART");
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = K_CPY_BPRBVRG;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        BPCPNHIS.INFO.OLD_DAT_PT = BPRORIS;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRBVRG;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "HISTORYEND");
    }
    public void B040_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBVRG);
        B051_STARTBR_PROCESS();
        if (pgmRtn) return;
        B052_READNEXT_PROCESS();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_NOTFIND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CNT = 0;
        while (WS_TBL_FLAG != 'N' 
            && WS_CNT <= 1000) {
            WS_CNT = WS_CNT + 1;
            R020_TRANS_DATA_OUPUT();
            if (pgmRtn) return;
            B052_READNEXT_PROCESS();
            if (pgmRtn) return;
        }
        B053_ENDBR_PROCESS();
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
        SCCFMT.FMTID = BPCSBVRG.OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOBVRO;
        SCCFMT.DATA_LEN = 450;
        IBS.FMT(SCCGWA, SCCFMT);
        CEP.TRC(SCCGWA, BPCOBVRO);
    }
    public void R010_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = BPCSBVRG.OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_BVRG_DETAIL;
        SCCFMT.DATA_LEN = 449;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        BPRBVRG.KEY.BV_CODE = BPCSBVRG.BV_CODE;
        BPRBVRG.KEY.BR = BPCSBVRG.BR_INFO[WS_J-1].BR;
        BPRBVRG.BV_CNM = BPCFBVQU.TX_DATA.BV_CNM;
        BPRBVRG.BV_ENM = BPCFBVQU.TX_DATA.BV_ENM;
        BPRBVRG.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBVRG.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPRBVRG.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRBVRG.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, WS_J);
        CEP.TRC(SCCGWA, BPCSBVRG.BR_INFO[WS_J-1].BR);
    }
    public void R010_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        BPCOBVRO.FUNC = BPCSBVRG.FUNC;
        BPCOBVRO.BV_CODE = BPCSBVRG.BV_CODE;
        for (WS_J = 1; WS_J <= 30 
            && BPCSBVRG.BR_INFO[WS_J-1].BR != 0; WS_J += 1) {
            BPCOBVRO.BR_INFO[WS_J-1].BR = BPCSBVRG.BR_INFO[WS_J-1].BR;
        }
        BPCOBVRO.BV_CNM = BPCFBVQU.TX_DATA.BV_CNM;
        BPCOBVRO.BV_ENM = BPCFBVQU.TX_DATA.BV_ENM;
        BPCOBVRO.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOBVRO.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCOBVRO.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCOBVRO.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
    }
    public void R020_TRANS_DATA_OUPUT() throws IOException,SQLException,Exception {
        WS_BVRG_DETAIL.WS_FUNC = BPCSBVRG.FUNC;
        WS_BVRG_DETAIL.WS_BV_CODE = BPRBVRG.KEY.BV_CODE;
        WS_BVRG_DETAIL.SDNCS_BR_INFO[WS_CNT-1].WS_BR = BPRBVRG.KEY.BR;
        WS_BVRG_DETAIL.WS_BV_CNM = BPRBVRG.BV_CNM;
        WS_BVRG_DETAIL.WS_BV_ENM = BPRBVRG.BV_ENM;
        WS_BVRG_DETAIL.WS_UPD_DATE = BPRBVRG.UPD_DATE;
        WS_BVRG_DETAIL.WS_UPD_TLR = BPRBVRG.UPD_TLR;
        WS_BVRG_DETAIL.WS_CRT_DATE = BPRBVRG.CRT_DATE;
        WS_BVRG_DETAIL.WS_CRT_TLR = BPRBVRG.CRT_TLR;
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, WS_BVRG_DETAIL.SDNCS_BR_INFO[WS_CNT-1].WS_BR);
    }
    public void B051_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBVRG);
        BPRBVRG.KEY.BV_CODE = BPCSBVRG.BV_CODE;
        if (BPCSBVRG.FUNC == 'M') {
            BPRBVRG.KEY.BR = 0;
        } else {
            BPRBVRG.KEY.BR = BPCSBVRG.BR_INFO[1-1].BR;
        }
        BPCRBVRB.INFO.FUNC = 'B';
        BPCRBVRB.POINTER = BPRBVRG;
        BPCRBVRB.LEN = 300;
        S000_CALL_BPZRBVRB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRBVRG.KEY.BR);
    }
    public void B052_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCRBVRB.INFO.FUNC = 'N';
        BPCRBVRB.POINTER = BPRBVRG;
        BPCRBVRB.LEN = 300;
        S000_CALL_BPZRBVRB();
        if (pgmRtn) return;
        if (BPCRBVRB.RETURN_INFO == 'F') {
            WS_TBL_FLAG = 'Y';
        } else if (BPCRBVRB.RETURN_INFO == 'N') {
            WS_TBL_FLAG = 'N';
        } else {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B053_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCRBVRB.INFO.FUNC = 'E';
        BPCRBVRB.LEN = 300;
        BPCRBVRB.POINTER = BPRBVRG;
        S000_CALL_BPZRBVRB();
        if (pgmRtn) return;
        if (BPCRBVRB.RETURN_INFO != 'F') {
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
        SCCMPAG.MAX_COL_NO = 276;
        SCCMPAG.SCR_ROW_CNT = 20;
        SCCMPAG.SCR_COL_CNT = 6;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B050_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOBVGO);
        BPCOBVGO.FUNC = BPCSBVRG.FUNC;
        BPCOBVGO.BV_CODE = BPRBVRG.KEY.BV_CODE;
        BPCOBVGO.BR = BPRBVRG.KEY.BR;
        BPCOBVGO.BV_CNM = BPRBVRG.BV_CNM;
        BPCOBVGO.BV_ENM = BPRBVRG.BV_ENM;
        BPCOBVGO.UPD_DATE = BPRBVRG.UPD_DATE;
        BPCOBVGO.UPD_TLR = BPRBVRG.UPD_TLR;
        BPCOBVGO.CRT_DATE = BPRBVRG.CRT_DATE;
        BPCOBVGO.CRT_TLR = BPRBVRG.CRT_TLR;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOBVGO);
        SCCMPAG.DATA_LEN = 276;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRBVRB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_BVRG, BPCRBVRB);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_BPZRBVRG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_BVRG, BPCTBVRG);
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
