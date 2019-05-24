package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2731 {
    String K_OUTPUT_FMT = "BP147";
    int K_NUM_1 = 1;
    char K_TRAN_RECALL_STS = 'N';
    String CPN_R_ADW_FLSH = "BP-R-ADW-FLSH       ";
    String CPN_R_ADW_FLSA = "BP-R-ADW-FLSA       ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_FLSA_BR = 0;
    int WS_CCY = 0;
    int WS_CCY_G = 0;
    int WS_CCY_ADD_CNT = 0;
    String WS_FLSA_PLBOX_NO = " ";
    BPOT2731_WS_GRP_CCY_INFO WS_GRP_CCY_INFO = new BPOT2731_WS_GRP_CCY_INFO();
    char WS_FUNC_FLG = ' ';
    char WS_TRAN_TYPE_FLG = ' ';
    char WS_GRP_FLG = ' ';
    char WS_FLSA_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRFLSH BPCRFLSH = new BPCRFLSH();
    BPCRFLSA BPCRFLSA = new BPCRFLSA();
    BPCOFLSR BPCOFLSR = new BPCOFLSR();
    BPRFLSH BPRFLSH = new BPRFLSH();
    BPRFLSA BPRFLSA = new BPRFLSA();
    SCCGWA SCCGWA;
    BPB2731_AWA_2731 BPB2731_AWA_2731;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT2731 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2731_AWA_2731>");
        BPB2731_AWA_2731 = (BPB2731_AWA_2731) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_RECALL_FLSH_PROCESS();
        WS_TRAN_TYPE_FLG = BPB2731_AWA_2731.TRAN_TYP;
        if (WS_TRAN_TYPE_FLG == '1') {
            B030_RECALL_REGST_FLSA_PROC();
            R000_TRANS_DATA_OUTPUT();
        } else if (WS_TRAN_TYPE_FLG == '2') {
            B040_RECALL_TURNIN_FLSA_PROC();
            R000_TRANS_DATA_OUTPUT();
        } else if (WS_TRAN_TYPE_FLG == '3') {
            B050_RECALL_EXTRACT_FLSA_PROC();
            R000_TRANS_DATA_OUTPUT();
        } else {
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2731_AWA_2731.FUNC);
        CEP.TRC(SCCGWA, BPB2731_AWA_2731.TRAN_DT);
        CEP.TRC(SCCGWA, BPB2731_AWA_2731.JRNNO);
        WS_FUNC_FLG = BPB2731_AWA_2731.FUNC;
        if (WS_FUNC_FLG != 'C') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB2731_AWA_2731.FUNC_NO;
            S000_ERR_MSG_PROC();
        }
        if (BPB2731_AWA_2731.TRAN_DT != SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_VALDT_ERR;
            WS_FLD_NO = BPB2731_AWA_2731.TRAN_DT_NO;
            S000_ERR_MSG_PROC();
        }
        if (!BPB2731_AWA_2731.TRAN_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_MUST_BE_SAME_TLR;
            WS_FLD_NO = BPB2731_AWA_2731.TRAN_TLR_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B020_RECALL_FLSH_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFLSH);
        BPRFLSH.KEY.DATE = BPB2731_AWA_2731.TRAN_DT;
        BPRFLSH.KEY.JRNNO = BPB2731_AWA_2731.JRNNO;
        BPCRFLSH.POINTER = BPRFLSH;
        BPCRFLSH.LEN = 608;
        BPCRFLSH.INFO.FUNC = 'R';
        S000_CALL_BPZRFLSH();
        if (BPCRFLSH.RETURN_INFO == 'N') {
            CEP.TRC(SCCGWA, BPCRFLSH.RC);
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLSH_NOTFND;
            S000_ERR_MSG_PROC();
        } else {
            if (BPB2731_AWA_2731.TRAN_BR == BPRFLSH.BR 
                && BPB2731_AWA_2731.CAP_NO == BPRFLSH.CAP_NO 
                && BPB2731_AWA_2731.TRAN_TYP == BPRFLSH.FLS_TYP) {
                BPRFLSH.STS = K_TRAN_RECALL_STS;
                BPCRFLSH.INFO.FUNC = 'U';
                S000_CALL_BPZRFLSH();
                if (BPCRFLSH.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFLSH.RC);
                    S000_ERR_MSG_PROC();
                }
            } else {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLSH_NOTFND;
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B030_RECALL_REGST_FLSA_PROC() throws IOException,SQLException,Exception {
        B060_GROUP_CCY_VERSION();
        WS_FLSA_BR = BPB2731_AWA_2731.TRAN_BR;
        WS_FLSA_PLBOX_NO = BPB2731_AWA_2731.REC_PBNO;
        WS_FLSA_FLG = 'R';
        B070_MAINT_BPTFLSA_PROCESS();
    }
    public void B040_RECALL_TURNIN_FLSA_PROC() throws IOException,SQLException,Exception {
        B060_GROUP_CCY_VERSION();
        WS_FLSA_BR = BPB2731_AWA_2731.TRAN_BR;
        WS_FLSA_PLBOX_NO = BPB2731_AWA_2731.PAY_PBNO;
        WS_FLSA_FLG = 'P';
        B070_MAINT_BPTFLSA_PROCESS();
        WS_FLSA_BR = BPB2731_AWA_2731.TRAN_BR;
        WS_FLSA_PLBOX_NO = BPB2731_AWA_2731.REC_PBNO;
        WS_FLSA_FLG = 'R';
        B070_MAINT_BPTFLSA_PROCESS();
    }
    public void B050_RECALL_EXTRACT_FLSA_PROC() throws IOException,SQLException,Exception {
        B060_GROUP_CCY_VERSION();
        WS_FLSA_BR = BPB2731_AWA_2731.TRAN_BR;
        WS_FLSA_PLBOX_NO = BPB2731_AWA_2731.PAY_PBNO;
        WS_FLSA_FLG = 'P';
        B070_MAINT_BPTFLSA_PROCESS();
    }
    public void B060_GROUP_CCY_VERSION() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_GRP_CCY_INFO);
        for (WS_CCY = K_NUM_1; WS_CCY <= BPRFLSH.REC_NUM; WS_CCY += K_NUM_1) {
            WS_CCY_G = K_NUM_1;
            WS_GRP_FLG = ' ';
            while (WS_CCY_G <= BPRFLSH.REC_NUM 
                && WS_GRP_FLG != 'N' 
                && WS_GRP_FLG != 'Y') {
                if (WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_G-1].WS_FLS_CCY.trim().length() == 0) {
                    WS_GRP_FLG = 'Y';
                } else if (BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_CCY.equalsIgnoreCase(WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_G-1].WS_FLS_CCY) 
                        && BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VAL == WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_G-1].WS_FLS_VAL 
                        && BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VER.equalsIgnoreCase(WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_G-1].WS_FLS_VER)) {
                    WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_G-1].WS_FLS_NUM += BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_NUM;
                    WS_GRP_FLG = 'N';
                } else {
                    WS_CCY_G += K_NUM_1;
                }
            }
            if (WS_GRP_FLG == 'Y') {
                WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_G-1].WS_FLS_CCY = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_CCY;
                WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_G-1].WS_FLS_VAL = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VAL;
                WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_G-1].WS_FLS_VER = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VER;
                WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY_G-1].WS_FLS_NUM = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_NUM;
                WS_CCY_ADD_CNT = WS_CCY_G;
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
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FLSA_NOTFND;
                S000_ERR_MSG_PROC();
            } else {
                if (WS_FLSA_FLG == 'R') {
                    if (WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY-1].WS_FLS_NUM > BPRFLSA.FLS_NUM) {
                        WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_ENGH_NUM_CCY;
                        S000_ERR_MSG_PROC();
                    } else {
                        CEP.TRC(SCCGWA, "UPDATE PAY FLSA");
                        BPRFLSA.FLS_NUM = BPRFLSA.FLS_NUM - WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY-1].WS_FLS_NUM;
                        CEP.TRC(SCCGWA, BPRFLSA.FLS_NUM);
                    }
                } else if (WS_FLSA_FLG == 'P') {
                    CEP.TRC(SCCGWA, "UPDATE REC FLSA");
                    BPRFLSA.FLS_NUM += WS_GRP_CCY_INFO.WS_GRP_CCYS[WS_CCY-1].WS_FLS_NUM;
                    CEP.TRC(SCCGWA, BPRFLSA.FLS_NUM);
                } else {
                }
                BPRFLSA.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                BPRFLSA.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCRFLSA.INFO.FUNC = 'U';
                CEP.TRC(SCCGWA, "UPDATE FLSA");
                S000_CALL_BPZRFLSA();
                CEP.TRC(SCCGWA, BPRFLSA.KEY.PLBOX_NO);
                if (BPCRFLSA.RC.RC_CODE != 0) {
                    WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRFLSA.RC);
                    S000_ERR_MSG_PROC();
                }
            }
        }
    }
    public void R000_TRANS_DATA_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFLSR);
        BPCOFLSR.TRAN_DT = BPRFLSH.KEY.DATE;
        BPCOFLSR.JRNNO = BPRFLSH.KEY.JRNNO;
        BPCOFLSR.CAP_NO = BPRFLSH.CAP_NO;
        BPCOFLSR.TRAN_TYP = BPRFLSH.FLS_TYP;
        BPCOFLSR.BR = BPRFLSH.BR;
        BPCOFLSR.TRAN_TLR = BPRFLSH.UPD_TLR;
        BPCOFLSR.REC_PBNO = BPB2731_AWA_2731.REC_PBNO;
        BPCOFLSR.REC_PBNO = BPB2731_AWA_2731.PAY_PBNO;
        BPCOFLSR.HLD_IDTP = BPRFLSH.HLD_IDTP;
        BPCOFLSR.HLD_IDNO = BPRFLSH.HLD_IDNO;
        BPCOFLSR.HLD_NM = BPRFLSH.HLD_NM;
        BPCOFLSR.HLD_PHN = BPRFLSH.HLD_PHN;
        BPCOFLSR.HLD_ADD = BPRFLSH.HLD_ADD;
        BPCOFLSR.HLD_EML = BPRFLSH.HLD_EML;
        BPCOFLSR.REC_NUM = BPRFLSH.REC_NUM;
        for (WS_CCY = K_NUM_1; WS_CCY <= BPRFLSH.REC_NUM; WS_CCY += K_NUM_1) {
            BPCOFLSR.FLS_DATA.CCYS[WS_CCY-1].FLS_CCY = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_CCY;
            BPCOFLSR.FLS_DATA.CCYS[WS_CCY-1].FLS_VAL = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VAL;
            BPCOFLSR.FLS_DATA.CCYS[WS_CCY-1].FLS_VER = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_VER;
            BPCOFLSR.FLS_DATA.CCYS[WS_CCY-1].FLS_HDNO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_HDNO;
            BPCOFLSR.FLS_DATA.CCYS[WS_CCY-1].BEG_NO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].BEG_NO;
            BPCOFLSR.FLS_DATA.CCYS[WS_CCY-1].END_NO = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].END_NO;
            BPCOFLSR.FLS_DATA.CCYS[WS_CCY-1].FLS_NUM = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_NUM;
            BPCOFLSR.FLS_DATA.CCYS[WS_CCY-1].FLS_SRC = BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_SRC;
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCOFLSR;
        SCCFMT.DATA_LEN = 3540;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZRFLSH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_FLSH, BPCRFLSH);
    }
    public void S000_CALL_BPZRFLSA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_FLSA, BPCRFLSA);
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
