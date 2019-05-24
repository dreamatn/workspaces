package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2730 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    boolean pgmRtn = false;
    int K_NUM_1 = 1;
    int K_NUM_5 = 5;
    int K_NUM_30 = 30;
    char K_STSW_CASH_FLG = '1';
    char K_STSW_POOL_FLG = '1';
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB       ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_R_ADW_FLSA = "BP-R-ADW-FLSA       ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_BROWSE_CNT = 0;
    char WS_FUNC_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCRFLSA BPCRFLSA = new BPCRFLSA();
    BPCOFLSC BPCOFLSC = new BPCOFLSC();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRFLSA BPRFLSA = new BPRFLSA();
    SCCGWA SCCGWA;
    BPB2730_AWA_2730 BPB2730_AWA_2730;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2730 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2730_AWA_2730>");
        BPB2730_AWA_2730 = (BPB2730_AWA_2730) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_BROWSE_FLSA_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB2730_AWA_2730.FUNC);
        WS_FUNC_FLG = BPB2730_AWA_2730.FUNC;
        if (WS_FUNC_FLG != 'B') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_FUNC_IS_INVALID;
            WS_FLD_NO = BPB2730_AWA_2730.FUNC_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPB2730_AWA_2730.TRAN_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BR_NOTFND;
            WS_FLD_NO = BPB2730_AWA_2730.TRAN_BR_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPRTLVB);
        BPRTLVB.KEY.BR = BPB2730_AWA_2730.TRAN_BR;
        BPRTLVB.KEY.PLBOX_NO = BPB2730_AWA_2730.PLBOX_NO;
        BPCTLVBF.POINTER = BPRTLVB;
        BPCTLVBF.LEN = 187;
        BPCTLVBF.INFO.FUNC = 'Q';
        S000_CALL_BPZTLVBF();
        if (pgmRtn) return;
        if (BPCTLVBF.RETURN_INFO == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLVB_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(3 - 1, 3 + 1 - 1).equalsIgnoreCase(K_STSW_CASH_FLG+"") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(4 - 1, 4 + 1 - 1).equalsIgnoreCase(K_STSW_POOL_FLG+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_CSHBOX_OR_LIBTLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_BROWSE_FLSA_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFLSA);
        BPRFLSA.KEY.BR = BPB2730_AWA_2730.TRAN_BR;
        BPRFLSA.KEY.PLBOX_NO = BPB2730_AWA_2730.PLBOX_NO;
        BPRFLSA.FLS_NUM = 0;
        B030_STARTBR_PLBOX();
        if (pgmRtn) return;
        B040_READ_NEXT_RECORDS();
        if (pgmRtn) return;
        B050_ENDBR_PROC();
        if (pgmRtn) return;
    }
    public void B030_STARTBR_PLBOX() throws IOException,SQLException,Exception {
        BPCRFLSA.POINTER = BPRFLSA;
        BPCRFLSA.LEN = 108;
        BPCRFLSA.INFO.FUNC = 'P';
        S000_CALL_BPZRFLSA();
        if (pgmRtn) return;
    }
    public void B040_READ_NEXT_RECORDS() throws IOException,SQLException,Exception {
        WS_BROWSE_CNT = 0;
        BPCRFLSA.POINTER = BPRFLSA;
        BPCRFLSA.LEN = 108;
        BPCRFLSA.INFO.FUNC = 'N';
        S000_CALL_BPZRFLSA();
        if (pgmRtn) return;
        while (BPCRFLSA.RETURN_INFO != 'N' 
            && SCCMPAG.FUNC != 'E') {
            WS_BROWSE_CNT += K_NUM_1;
            if (WS_BROWSE_CNT == K_NUM_1) {
                R000_TRANS_OUTPUT_TITLE();
                if (pgmRtn) return;
            }
            R010_TRANS_OUTPUT_FLSA();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_BROWSE_CNT);
            BPCRFLSA.POINTER = BPRFLSA;
            BPCRFLSA.LEN = 108;
            BPCRFLSA.INFO.FUNC = 'N';
            S000_CALL_BPZRFLSA();
            if (pgmRtn) return;
        }
    }
    public void B050_ENDBR_PROC() throws IOException,SQLException,Exception {
        BPCRFLSA.POINTER = BPRFLSA;
        BPCRFLSA.LEN = 108;
        BPCRFLSA.INFO.FUNC = 'E';
        S000_CALL_BPZRFLSA();
        if (pgmRtn) return;
    }
    public void R000_TRANS_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 54;
        SCCMPAG.SCR_ROW_CNT = (short) K_NUM_30;
        SCCMPAG.SCR_COL_CNT = (short) K_NUM_5;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void R010_TRANS_OUTPUT_FLSA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFLSC);
        BPCOFLSC.FLS_CCY = BPRFLSA.KEY.FLS_CCY;
        BPCOFLSC.FLS_VAL = BPRFLSA.KEY.FLS_VAL;
        BPCOFLSC.FLS_VER = BPRFLSA.KEY.FLS_VER;
        BPCOFLSC.FLS_NUM = BPRFLSA.FLS_NUM;
        BPCOFLSC.FLS_AMT = BPRFLSA.KEY.FLS_VAL * BPRFLSA.FLS_NUM;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOFLSC);
        SCCMPAG.DATA_LEN = 54;
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
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTLVBF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_ADW_TLVB, BPCTLVBF);
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
