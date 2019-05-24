package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2740 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int K_NUM_1 = 1;
    int K_NUM_9 = 9;
    int K_NUM_30 = 30;
    char K_PLBOX_CASH_TYPE = '3';
    char K_PLBOX_SBRCH_TYPE = '2';
    char K_PLBOX_BRCH_TYPE = '1';
    char K_STSW_CASH_FLG = '1';
    char K_STSW_POOL_FLG = '1';
    char K_TRAN_REGST = '1';
    char K_TRAN_RECALL_STS = 'N';
    char K_TRAN_RECALL_FLG = '4';
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB       ";
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB   ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_R_ADW_FLSH = "BP-R-ADW-FLSH       ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_CCY = 0;
    String WS_POOLBOX_NO = " ";
    String WS_CASHBOX_NO = " ";
    int WS_BROWSE_CNT = 0;
    char WS_FUNC_FLG = ' ';
    char WS_TRAN_TYP_FLG = ' ';
    char WS_FLSH_STS_FLG = ' ';
    char WS_TURNIN_TO_POOL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRFLSH BPCRFLSH = new BPCRFLSH();
    BPCOFLSQ BPCOFLSQ = new BPCOFLSQ();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRFLSH BPRFLSH = new BPRFLSH();
    SCCGWA SCCGWA;
    BPB2740_AWA_2740 BPB2740_AWA_2740;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2740 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2740_AWA_2740>");
        BPB2740_AWA_2740 = (BPB2740_AWA_2740) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_FLSH_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2740_AWA_2740.FUNC);
        WS_FUNC_FLG = BPB2740_AWA_2740.FUNC;
        if (WS_FUNC_FLG != 'B') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB2740_AWA_2740.FUNC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_FLSH_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFLSH);
        BPRFLSH.KEY.DATE = BPB2740_AWA_2740.TRAN_DT;
        WS_TRAN_TYP_FLG = BPB2740_AWA_2740.TRAN_TYP;
        BPRFLSH.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        WS_BROWSE_CNT = 0;
        CEP.TRC(SCCGWA, BPRFLSH.KEY.DATE);
        CEP.TRC(SCCGWA, WS_TRAN_TYP_FLG);
        CEP.TRC(SCCGWA, BPRFLSH.BR);
        if ((WS_TRAN_TYP_FLG == ' ' 
                || WS_TRAN_TYP_FLG == '4')) {
            CEP.TRC(SCCGWA, WS_TRAN_TYP_FLG);
            B070_GET_POOLBOX_NUMBER();
            if (pgmRtn) return;
            B080_GET_CASHBOX_NUMBER();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_POOLBOX_NO);
            CEP.TRC(SCCGWA, WS_CASHBOX_NO);
            if (WS_POOLBOX_NO.trim().length() == 0 
                && WS_CASHBOX_NO.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (WS_POOLBOX_NO.trim().length() > 0) {
                BPRFLSH.REC_PBNO = WS_POOLBOX_NO;
                BPRFLSH.PAY_PBNO = WS_POOLBOX_NO;
                B030_STARTBR_PLBOX();
                if (pgmRtn) return;
                B060_READNEXT_REC_AND_ENDBR();
                if (pgmRtn) return;
            }
            if (WS_CASHBOX_NO.trim().length() > 0) {
                BPRFLSH.REC_PBNO = WS_CASHBOX_NO;
                BPRFLSH.PAY_PBNO = WS_CASHBOX_NO;
                B030_STARTBR_PLBOX();
                if (pgmRtn) return;
                B060_READNEXT_REC_AND_ENDBR();
                if (pgmRtn) return;
            }
        } else if (WS_TRAN_TYP_FLG == '1') {
            B070_GET_POOLBOX_NUMBER();
            if (pgmRtn) return;
            B080_GET_CASHBOX_NUMBER();
            if (pgmRtn) return;
            if (WS_POOLBOX_NO.trim().length() == 0 
                && WS_CASHBOX_NO.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPRFLSH.FLS_TYP = WS_TRAN_TYP_FLG;
            WS_FLSH_STS_FLG = 'Y';
            BPRFLSH.STS = WS_FLSH_STS_FLG;
            BPRFLSH.REC_PBNO = WS_CASHBOX_NO;
            B040_STARTBR_REC_TYP();
            if (pgmRtn) return;
            B060_READNEXT_REC_AND_ENDBR();
            if (pgmRtn) return;
        } else if (WS_TRAN_TYP_FLG == '2') {
            B070_GET_POOLBOX_NUMBER();
            if (pgmRtn) return;
            B080_GET_CASHBOX_NUMBER();
            if (pgmRtn) return;
            if (WS_POOLBOX_NO.trim().length() == 0 
                && WS_CASHBOX_NO.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPRFLSH.FLS_TYP = WS_TRAN_TYP_FLG;
            WS_FLSH_STS_FLG = 'Y';
            BPRFLSH.STS = WS_FLSH_STS_FLG;
            if (WS_POOLBOX_NO.trim().length() > 0) {
                BPRFLSH.REC_PBNO = WS_POOLBOX_NO;
                B040_STARTBR_REC_TYP();
                if (pgmRtn) return;
                B060_READNEXT_REC_AND_ENDBR();
                if (pgmRtn) return;
            }
            if (WS_CASHBOX_NO.trim().length() > 0) {
                BPRFLSH.PAY_PBNO = WS_CASHBOX_NO;
                B050_STARTBR_PAY_TYP();
                if (pgmRtn) return;
                B060_READNEXT_REC_AND_ENDBR();
                if (pgmRtn) return;
            }
        } else if (WS_TRAN_TYP_FLG == '3') {
            B070_GET_POOLBOX_NUMBER();
            if (pgmRtn) return;
            B080_GET_CASHBOX_NUMBER();
            if (pgmRtn) return;
            if (WS_POOLBOX_NO.trim().length() == 0 
                && WS_CASHBOX_NO.trim().length() == 0) {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPRFLSH.FLS_TYP = WS_TRAN_TYP_FLG;
            WS_FLSH_STS_FLG = 'Y';
            BPRFLSH.STS = WS_FLSH_STS_FLG;
            BPRFLSH.PAY_PBNO = WS_POOLBOX_NO;
            B050_STARTBR_PAY_TYP();
            if (pgmRtn) return;
            B060_READNEXT_REC_AND_ENDBR();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B030_STARTBR_PLBOX() throws IOException,SQLException,Exception {
        BPCRFLSH.POINTER = BPRFLSH;
        BPCRFLSH.LEN = 608;
        BPCRFLSH.INFO.FUNC = 'P';
        S000_CALL_BPZRFLSH();
        if (pgmRtn) return;
    }
    public void B040_STARTBR_REC_TYP() throws IOException,SQLException,Exception {
        BPRFLSH.STS = WS_FLSH_STS_FLG;
        BPCRFLSH.POINTER = BPRFLSH;
        BPCRFLSH.LEN = 608;
        BPCRFLSH.INFO.FUNC = 'X';
        S000_CALL_BPZRFLSH();
        if (pgmRtn) return;
    }
    public void B050_STARTBR_PAY_TYP() throws IOException,SQLException,Exception {
        BPRFLSH.STS = WS_FLSH_STS_FLG;
        BPCRFLSH.POINTER = BPRFLSH;
        BPCRFLSH.LEN = 608;
        BPCRFLSH.INFO.FUNC = 'Y';
        S000_CALL_BPZRFLSH();
        if (pgmRtn) return;
    }
    public void B060_READNEXT_REC_AND_ENDBR() throws IOException,SQLException,Exception {
        BPCRFLSH.POINTER = BPRFLSH;
        BPCRFLSH.LEN = 608;
        BPCRFLSH.INFO.FUNC = 'N';
        S000_CALL_BPZRFLSH();
        if (pgmRtn) return;
        while (BPCRFLSH.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            WS_BROWSE_CNT += K_NUM_1;
            if (WS_BROWSE_CNT == K_NUM_1) {
                R000_TRANS_OUTPUT_TITLE();
                if (pgmRtn) return;
            }
            if (WS_TRAN_TYP_FLG == '4') {
                if (BPRFLSH.STS == K_TRAN_RECALL_STS) {
                    R010_TRANS_OUTPUT_FLSH();
                    if (pgmRtn) return;
                }
            } else {
                R010_TRANS_OUTPUT_FLSH();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, WS_BROWSE_CNT);
            BPCRFLSH.POINTER = BPRFLSH;
            BPCRFLSH.LEN = 608;
            BPCRFLSH.INFO.FUNC = 'N';
            S000_CALL_BPZRFLSH();
            if (pgmRtn) return;
        }
        BPCRFLSH.POINTER = BPRFLSH;
        BPCRFLSH.LEN = 608;
        BPCRFLSH.INFO.FUNC = 'E';
        S000_CALL_BPZRFLSH();
        if (pgmRtn) return;
    }
    public void B070_GET_POOLBOX_NUMBER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_POOL_FLG+"")) {
            WS_POOLBOX_NO = " ";
            IBS.init(SCCGWA, BPRTLVB);
            BPRTLVB.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPRTLVB.PLBOX_TP = ALL.charAt(0);
            BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.FUNC = 'T';
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCRTLVB.RETURN_INFO);
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.FUNC = 'N';
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
            while (BPCRTLVB.RETURN_INFO != 'N') {
                CEP.TRC(SCCGWA, BPRTLVB.PLBOX_TP);
                if (BPRTLVB.PLBOX_TP == K_PLBOX_BRCH_TYPE 
                    || BPRTLVB.PLBOX_TP == K_PLBOX_SBRCH_TYPE) {
                    WS_POOLBOX_NO = BPRTLVB.KEY.PLBOX_NO;
                }
                BPCRTLVB.INFO.POINTER = BPRTLVB;
                BPCRTLVB.INFO.LEN = 187;
                BPCRTLVB.INFO.FUNC = 'N';
                S000_CALL_BPZRTLVB();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCRTLVB.RETURN_INFO);
            }
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            BPCRTLVB.INFO.FUNC = 'E';
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
        }
    }
    public void B080_GET_CASHBOX_NUMBER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_STSW);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_CASH_FLG+"")) {
            WS_CASHBOX_NO = " ";
            IBS.init(SCCGWA, BPRTLVB);
            BPRTLVB.PLBOX_TP = K_PLBOX_CASH_TYPE;
            BPRTLVB.CRNT_TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCTLVBF.POINTER = BPRTLVB;
            BPCTLVBF.LEN = 187;
            BPCTLVBF.INFO.FUNC = 'I';
            S000_CALL_BPZTLVBF();
            if (pgmRtn) return;
            WS_CASHBOX_NO = BPRTLVB.KEY.PLBOX_NO;
        }
    }
    public void R000_TRANS_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 62;
        SCCMPAG.SCR_ROW_CNT = (short) K_NUM_30;
        SCCMPAG.SCR_COL_CNT = (short) K_NUM_9;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R010_TRANS_OUTPUT_FLSH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFLSQ);
        BPCOFLSQ.TRAN_DT = BPRFLSH.KEY.DATE;
        BPCOFLSQ.JRNNO = BPRFLSH.KEY.JRNNO;
        BPCOFLSQ.CAP_NO = BPRFLSH.CAP_NO;
        if (BPRFLSH.STS == K_TRAN_RECALL_STS) {
            BPCOFLSQ.TRAN_TYP = K_TRAN_RECALL_FLG;
        } else {
            BPCOFLSQ.TRAN_TYP = BPRFLSH.FLS_TYP;
        }
        BPCOFLSQ.BR = BPRFLSH.BR;
        BPCOFLSQ.TLR = BPRFLSH.UPD_TLR;
        BPCOFLSQ.REC_PBNO = BPRFLSH.REC_PBNO;
        BPCOFLSQ.PAY_PBNO = BPRFLSH.PAY_PBNO;
        for (WS_CCY = K_NUM_1; WS_CCY <= BPRFLSH.REC_NUM; WS_CCY += K_NUM_1) {
            BPCOFLSQ.FLS_NUM += BPRFLSH.REDEFINES11.DATA_TEXT[WS_CCY-1].FLS_NUM;
        }
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOFLSQ);
        SCCMPAG.DATA_LEN = 62;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TLR_INFO_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZRFLSH() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_FLSH, BPCRFLSH);
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
