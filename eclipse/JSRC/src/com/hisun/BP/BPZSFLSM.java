package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSFLSM {
    int K_NUM_1 = 1;
    char K_RUN_MODE = 'O';
    String K_FLSCAPT_SEQ = "CAPT";
    String K_SEQ_TYPE = "SEQ";
    String K_HIS_REGST = "FALSE NOTES REGISTER";
    String K_HIS_TURNIN = "FALSE NOTES TURNIN";
    String K_HIS_EXTRACT = "FALSE NOTES EXTRACT";
    String K_CPY_BPRFLSA = "BPRFLSA";
    String K_CPY_BPRFLSH = "BPRFLSH";
    String CPN_S_GET_SEQ = "BP-S-GET-SEQ        ";
    String CPN_R_ADW_FLSH = "BP-R-ADW-FLSH       ";
    String CPN_R_ADW_FLSA = "BP-R-ADW-FLSA       ";
    String CPN_REC_NHIS = "BP-REC-NHIS";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_FLSA_BR = 0;
    int WS_CCY = 0;
    int WS_CCY_GP = 0;
    int WS_CCY_ADD_CNT = 0;
    String WS_FLSA_PLBOX_NO = " ";
    int WS_FLS_CAPNO = 0;
    BPZSFLSM_WS_GRP_CCY_INFO WS_GRP_CCY_INFO = new BPZSFLSM_WS_GRP_CCY_INFO();
    String WS_FMT_ID = " ";
    String WS_TX_RMK = " ";
    char WS_GRP_FLG = ' ';
    char WS_FLSA_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCRFLSH BPCRFLSH = new BPCRFLSH();
    BPCRFLSA BPCRFLSA = new BPCRFLSA();
    BPCOFLSO BPCOFLSO = new BPCOFLSO();
    BPCOFLST BPCOFLST = new BPCOFLST();
    BPRFLSH BPRFLSH = new BPRFLSH();
    BPRFLSA BPRFLSA = new BPRFLSA();
    BPRFLSA BPROLSA = new BPRFLSA();
    SCCGWA SCCGWA;
    BPCOFLSM BPCOFLSM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCOFLSM BPCOFLSM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOFLSM = BPCOFLSM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZSFLSM return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRFLSH);
        IBS.init(SCCGWA, BPCRFLSA);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOFLSM.FUNC);
        if (BPCOFLSM.FUNC == 'R') {
            B010_ADD_FLSH_PROCESS();
            B020_REGISTER_FLSA_PROCESS();
            R000_TRANS_DATA_OUTPUT();
        } else if (BPCOFLSM.FUNC == 'T') {
            B010_ADD_FLSH_PROCESS();
            B030_TURNIN_FLSA_PROCESS();
            R000_TRANS_DATA_OUTPUT();
        } else if (BPCOFLSM.FUNC == 'E') {
            B010_ADD_FLSH_PROCESS();
            B040_EXTRACT_FLSA_PROCESS();
            R000_TRANS_DATA_OUTPUT();
        } else {
            CEP.TRC(SCCGWA, "ERR");
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
            S000_ERR_MSG_PROC();
        }
    }
    public void B010_ADD_FLSH_PROCESS() throws IOException,SQLException,Exception {
        B010_01_GET_FLS_CAPTNO();
        IBS.init(SCCGWA, BPRFLSH);
        R020_TRANS_FLSH_DATA_PARM();
        BPCRFLSH.POINTER = BPRFLSH;
        BPCRFLSH.LEN = 608;
        BPCRFLSH.INFO.FUNC = 'A';
        S000_CALL_BPZRFLSH();
        if (BPCRFLSH.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCRFLSH.RC);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFLSH.RC);
            S000_ERR_MSG_PROC();
        }
        WS_FMT_ID = K_CPY_BPRFLSH;
        B080_ADD_HISTORY_RECORD();
    }
    public void B010_01_GET_FLS_CAPTNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = K_SEQ_TYPE;
        BPCSGSEQ.CODE = K_FLSCAPT_SEQ;
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = K_RUN_MODE;
        BPCSGSEQ.NUM = (short) K_NUM_1;
        S000_CALL_BPZSGSEQ();
        WS_FLS_CAPNO = (int) BPCSGSEQ.SEQ;
    }
    public void B020_REGISTER_FLSA_PROCESS() throws IOException,SQLException,Exception {
        B060_GROUP_CCY_VERSION();
        WS_FLSA_BR = BPCOFLSM.REC_BR;
        WS_FLSA_PLBOX_NO = BPCOFLSM.REC_PBNO;
        WS_FLSA_FLG = 'R';
        B070_MAINT_BPTFLSA_PROCESS();
    }
    public void B030_TURNIN_FLSA_PROCESS() throws IOException,SQLException,Exception {
        B060_GROUP_CCY_VERSION();
        WS_FLSA_BR = BPCOFLSM.IN_BR;
        WS_FLSA_PLBOX_NO = BPCOFLSM.PAY_PBNO;
        WS_FLSA_FLG = 'P';
        B070_MAINT_BPTFLSA_PROCESS();
        WS_FLSA_BR = BPCOFLSM.REC_BR;
        WS_FLSA_PLBOX_NO = BPCOFLSM.REC_PBNO;
        WS_FLSA_FLG = 'R';
        B070_MAINT_BPTFLSA_PROCESS();
    }
    public void B040_EXTRACT_FLSA_PROCESS() throws IOException,SQLException,Exception {
        B060_GROUP_CCY_VERSION();
        WS_FLSA_BR = BPCOFLSM.IN_BR;
        WS_FLSA_PLBOX_NO = BPCOFLSM.PAY_PBNO;
        WS_FLSA_FLG = 'P';
        B070_MAINT_BPTFLSA_PROCESS();
    }
    public void B060_GROUP_CCY_VERSION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_GRP_CCY_INFO);
        for (WS_CCY = K_NUM_1; WS_CCY <= BPCOFLSM.REC_NUM; WS_CCY += K_NUM_1) {
            WS_CCY_GP = K_NUM_1;
            WS_GRP_FLG = ' ';
            while (WS_CCY_GP <= BPCOFLSM.REC_NUM 
                && WS_GRP_FLG != 'N' 
                && WS_GRP_FLG != 'Y') {
                if (WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_GP-1].WS_FLS_CCY.trim().length() == 0) {
                    WS_GRP_FLG = 'Y';
                } else if (BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_CCY.equalsIgnoreCase(WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_GP-1].WS_FLS_CCY) 
                        && BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_VAL == WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_GP-1].WS_FLS_VAL 
                        && BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_VER.equalsIgnoreCase(WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_GP-1].WS_FLS_VER)) {
                    WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_GP-1].WS_FLS_NUM += BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_NUM;
                    WS_GRP_FLG = 'N';
                } else {
                    WS_CCY_GP += K_NUM_1;
                }
            }
            if (WS_GRP_FLG == 'Y') {
                WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_GP-1].WS_FLS_CCY = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_CCY;
                WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_GP-1].WS_FLS_VAL = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_VAL;
                WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_GP-1].WS_FLS_VER = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_VER;
                WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_GP-1].WS_FLS_NUM = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_NUM;
                WS_CCY_ADD_CNT = WS_CCY_GP;
            }
        }
    }
    public void B070_MAINT_BPTFLSA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFLSA);
        BPRFLSA.KEY.BR = WS_FLSA_BR;
        BPRFLSA.KEY.PLBOX_NO = WS_FLSA_PLBOX_NO;
        CEP.TRC(SCCGWA, WS_CCY_ADD_CNT);
        for (WS_CCY = K_NUM_1; WS_CCY <= WS_CCY_ADD_CNT; WS_CCY += K_NUM_1) {
            BPRFLSA.KEY.FLS_CCY = WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY-1].WS_FLS_CCY;
            BPRFLSA.KEY.FLS_VAL = WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY-1].WS_FLS_VAL;
            BPRFLSA.KEY.FLS_VER = WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY-1].WS_FLS_VER;
            BPCRFLSA.POINTER = BPRFLSA;
            BPCRFLSA.LEN = 108;
            BPCRFLSA.INFO.FUNC = 'R';
            CEP.TRC(SCCGWA, "READUPDATE FLSA");
            S000_CALL_BPZRFLSA();
            if (BPCRFLSA.RETURN_INFO == 'N') {
                if (WS_FLSA_FLG == 'R') {
                    BPRFLSA.FLS_NUM = WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY-1].WS_FLS_NUM;
                    BPRFLSA.UPD_DT = BPCOFLSM.TRAN_DT;
                    BPRFLSA.UPD_TLR = BPCOFLSM.IN_TLR;
                    BPRFLSA.OPEN_DT = BPCOFLSM.TRAN_DT;
                    BPRFLSA.OPEN_TLR = BPCOFLSM.IN_TLR;
                    BPCRFLSA.INFO.FUNC = 'A';
                    CEP.TRC(SCCGWA, "CREATE FLSA");
                    S000_CALL_BPZRFLSA();
                    CEP.TRC(SCCGWA, BPRFLSA.KEY.PLBOX_NO);
                    if (BPCRFLSA.RC.RC_CODE != 0) {
                        WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFLSA.RC);
                        S000_ERR_MSG_PROC();
                    }
                    BPCPNHIS.INFO.FMT_ID = K_CPY_BPRFLSA;
                    B080_ADD_HISTORY_RECORD();
                } else if (WS_FLSA_FLG == 'P') {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFLSA.RC);
                    S000_ERR_MSG_PROC();
                }
            } else {
                IBS.CLONE(SCCGWA, BPRFLSA, BPROLSA);
                if (WS_FLSA_FLG == 'R') {
                    CEP.TRC(SCCGWA, "UPDATE REC FLSA");
                    BPRFLSA.FLS_NUM += WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY-1].WS_FLS_NUM;
                    CEP.TRC(SCCGWA, BPRFLSA.FLS_NUM);
                } else if (WS_FLSA_FLG == 'P') {
                    if (WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY-1].WS_FLS_NUM > BPRFLSA.FLS_NUM) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ENGH_NUM_CCY;
                        S000_ERR_MSG_PROC();
                    } else {
                        CEP.TRC(SCCGWA, "UPDATE PAY FLSA");
                        BPRFLSA.FLS_NUM = BPRFLSA.FLS_NUM - WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY-1].WS_FLS_NUM;
                        CEP.TRC(SCCGWA, BPRFLSA.FLS_NUM);
                    }
                }
                BPRFLSA.UPD_DT = BPCOFLSM.TRAN_DT;
                BPRFLSA.UPD_TLR = BPCOFLSM.IN_TLR;
                BPCRFLSA.INFO.FUNC = 'U';
                CEP.TRC(SCCGWA, "UPDATE FLSA");
                S000_CALL_BPZRFLSA();
                CEP.TRC(SCCGWA, BPRFLSA.KEY.PLBOX_NO);
                if (BPCRFLSA.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFLSA.RC);
                    S000_ERR_MSG_PROC();
                }
                WS_FMT_ID = K_CPY_BPRFLSA;
                B090_UPD_HISTORY_RECORD();
            }
        }
    }
    public void B080_ADD_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.FMT_ID = WS_FMT_ID;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (BPCOFLSM.FUNC == 'R') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REGST;
        } else if (BPCOFLSM.FUNC == 'T') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_TURNIN;
        } else if (BPCOFLSM.FUNC == 'E') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_EXTRACT;
        }
        S000_CALL_BPZPNHIS();
    }
    public void B090_UPD_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.FMT_ID = WS_FMT_ID;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        if (BPCOFLSM.FUNC == 'R') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_REGST;
        } else if (BPCOFLSM.FUNC == 'T') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_TURNIN;
        } else if (BPCOFLSM.FUNC == 'E') {
            BPCPNHIS.INFO.TX_RMK = K_HIS_EXTRACT;
        }
        BPCPNHIS.INFO.OLD_DAT_PT = BPROLSA;
        BPCPNHIS.INFO.NEW_DAT_PT = BPRFLSA;
        S000_CALL_BPZPNHIS();
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        if (BPCOFLSM.FUNC == 'R') {
            CEP.TRC(SCCGWA, BPCOFLSM.FUNC);
            IBS.init(SCCGWA, BPCOFLSO);
            R010_TRANS_OUTPUT_FLSO();
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = BPCOFLSM.OUTPUT_FMT;
            SCCFMT.DATA_PTR = BPCOFLSO;
            SCCFMT.DATA_LEN = 3535;
            IBS.FMT(SCCGWA, SCCFMT);
        } else if ((BPCOFLSM.FUNC == 'T' 
                || BPCOFLSM.FUNC == 'E')) {
            CEP.TRC(SCCGWA, BPCOFLSM.FUNC);
            IBS.init(SCCGWA, BPCOFLST);
            R010_TRANS_OUTPUT_FLST();
            IBS.init(SCCGWA, SCCFMT);
            SCCFMT.FMTID = BPCOFLSM.OUTPUT_FMT;
            SCCFMT.DATA_PTR = BPCOFLST;
            SCCFMT.DATA_LEN = 3037;
            IBS.FMT(SCCGWA, SCCFMT);
        }
    }
    public void R010_TRANS_OUTPUT_FLSO() throws IOException,SQLException,Exception {
        BPCOFLSO.TRAN_DT = BPRFLSH.KEY.DATE;
        BPCOFLSO.JRNNO = BPRFLSH.KEY.JRNNO;
        BPCOFLSO.BR = BPRFLSH.BR;
        BPCOFLSO.IN_TLR = BPRFLSH.UPD_TLR;
        BPCOFLSO.REC_TLR = BPRFLSH.UPD_TLR;
        BPCOFLSO.CAP_NO = BPRFLSH.CAP_NO;
        BPCOFLSO.HLD_IDTP = BPRFLSH.HLD_IDTP;
        BPCOFLSO.HLD_IDNO = BPRFLSH.HLD_IDNO;
        BPCOFLSO.HLD_NM = BPRFLSH.HLD_NM;
        BPCOFLSO.HLD_PHN = BPRFLSH.HLD_PHN;
        BPCOFLSO.HLD_ADD = BPRFLSH.HLD_ADD;
        BPCOFLSO.HLD_EML = BPRFLSH.HLD_EML;
        BPCOFLSO.REC_NUM = BPRFLSH.REC_NUM;
        for (WS_CCY = K_NUM_1; WS_CCY <= BPRFLSH.REC_NUM; WS_CCY += K_NUM_1) {
            BPCOFLSO.FLS_DATA.CCYS[WS_CCY-1].FLS_CCY = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_CCY;
            BPCOFLSO.FLS_DATA.CCYS[WS_CCY-1].FLS_VAL = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VAL;
            BPCOFLSO.FLS_DATA.CCYS[WS_CCY-1].FLS_VER = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VER;
            BPCOFLSO.FLS_DATA.CCYS[WS_CCY-1].FLS_HDNO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_HDNO;
            BPCOFLSO.FLS_DATA.CCYS[WS_CCY-1].BEG_NO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].BEG_NO;
            BPCOFLSO.FLS_DATA.CCYS[WS_CCY-1].END_NO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].END_NO;
            BPCOFLSO.FLS_DATA.CCYS[WS_CCY-1].FLS_NUM = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_NUM;
            BPCOFLSO.FLS_DATA.CCYS[WS_CCY-1].FLS_SRC = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_SRC;
        }
    }
    public void R010_TRANS_OUTPUT_FLST() throws IOException,SQLException,Exception {
        BPCOFLST.TRAN_DT = BPRFLSH.KEY.DATE;
        BPCOFLST.JRNNO = BPRFLSH.KEY.JRNNO;
        BPCOFLST.BR = BPRFLSH.BR;
        BPCOFLST.IN_TLR = BPRFLSH.UPD_TLR;
        BPCOFLST.REC_TLR = BPCOFLSM.REC_TLR;
        BPCOFLST.CAP_NO = BPRFLSH.CAP_NO;
        BPCOFLST.REC_NUM = BPRFLSH.REC_NUM;
        for (WS_CCY = K_NUM_1; WS_CCY <= BPCOFLSM.REC_NUM; WS_CCY += K_NUM_1) {
            BPCOFLST.FLS_DATA.CCYS[WS_CCY-1].FLS_CCY = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_CCY;
            BPCOFLST.FLS_DATA.CCYS[WS_CCY-1].FLS_VAL = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VAL;
            BPCOFLST.FLS_DATA.CCYS[WS_CCY-1].FLS_VER = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VER;
            BPCOFLST.FLS_DATA.CCYS[WS_CCY-1].FLS_HDNO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_HDNO;
            BPCOFLST.FLS_DATA.CCYS[WS_CCY-1].BEG_NO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].BEG_NO;
            BPCOFLST.FLS_DATA.CCYS[WS_CCY-1].END_NO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].END_NO;
            BPCOFLST.FLS_DATA.CCYS[WS_CCY-1].FLS_NUM = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_NUM;
            BPCOFLST.FLS_DATA.CCYS[WS_CCY-1].FLS_SRC = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_SRC;
        }
    }
    public void R020_TRANS_FLSH_DATA_PARM() throws IOException,SQLException,Exception {
        BPRFLSH.KEY.DATE = BPCOFLSM.TRAN_DT;
        BPRFLSH.KEY.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPRFLSH.BR = BPCOFLSM.IN_BR;
        BPRFLSH.CAP_NO = WS_FLS_CAPNO;
        BPRFLSH.FLS_TYP = BPCOFLSM.TX_TYP;
        BPRFLSH.HLD_IDTP = BPCOFLSM.HLD_IDTP;
        BPRFLSH.HLD_IDNO = BPCOFLSM.HLD_IDNO;
        BPRFLSH.HLD_NM = BPCOFLSM.HLD_NM;
        BPRFLSH.HLD_PHN = BPCOFLSM.HLD_PHN;
        BPRFLSH.HLD_ADD = BPCOFLSM.HLD_ADD;
        BPRFLSH.HLD_EML = BPCOFLSM.HLD_EML;
        BPRFLSH.REC_PBNO = BPCOFLSM.REC_PBNO;
        BPRFLSH.PAY_PBNO = BPCOFLSM.PAY_PBNO;
        BPRFLSH.STS = BPCOFLSM.STATUS;
        BPRFLSH.UPD_DT = BPCOFLSM.TRAN_DT;
        BPRFLSH.UPD_TLR = BPCOFLSM.IN_TLR;
        BPRFLSH.OPEN_DT = BPCOFLSM.TRAN_DT;
        BPRFLSH.OPEN_TLR = BPCOFLSM.IN_TLR;
        BPRFLSH.REC_NUM = BPCOFLSM.REC_NUM;
        BPRFLSH.BLOB_FLS_DATA = IBS.CLS2CPY(SCCGWA, BPRFLSH.REDEFINES11);
        CEP.TRC(SCCGWA, BPRFLSH.REDEFINES11.DATA_LEN);
        for (WS_CCY = K_NUM_1; WS_CCY <= BPCOFLSM.REC_NUM; WS_CCY += K_NUM_1) {
            BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_CCY = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_CCY;
            BPRFLSH.BLOB_FLS_DATA = IBS.CLS2CPY(SCCGWA, BPRFLSH.REDEFINES11);
            BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VAL = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_VAL;
            BPRFLSH.BLOB_FLS_DATA = IBS.CLS2CPY(SCCGWA, BPRFLSH.REDEFINES11);
            BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VER = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_VER;
            BPRFLSH.BLOB_FLS_DATA = IBS.CLS2CPY(SCCGWA, BPRFLSH.REDEFINES11);
            BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_HDNO = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_HDNO;
            BPRFLSH.BLOB_FLS_DATA = IBS.CLS2CPY(SCCGWA, BPRFLSH.REDEFINES11);
            BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].BEG_NO = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_BGNO;
            BPRFLSH.BLOB_FLS_DATA = IBS.CLS2CPY(SCCGWA, BPRFLSH.REDEFINES11);
            BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].END_NO = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_EDNO;
            BPRFLSH.BLOB_FLS_DATA = IBS.CLS2CPY(SCCGWA, BPRFLSH.REDEFINES11);
            BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_NUM = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_NUM;
            BPRFLSH.BLOB_FLS_DATA = IBS.CLS2CPY(SCCGWA, BPRFLSH.REDEFINES11);
            BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_SRC = BPCOFLSM.CCYS.CCY_DATA[WS_CCY-1].FLS_SRC;
            BPRFLSH.BLOB_FLS_DATA = IBS.CLS2CPY(SCCGWA, BPRFLSH.REDEFINES11);
        }
    }
    public void S000_CALL_BPZSGSEQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GET_SEQ, BPCSGSEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZRFLSH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_FLSH, BPCRFLSH);
    }
    public void S000_CALL_BPZRFLSA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_FLSA, BPCRFLSA);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_REC_NHIS, BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, BPCPNHIS.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
