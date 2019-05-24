package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT2901 {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    char K_F_CASH_LIB = '1';
    char K_Z_CASH_LIB = '2';
    char K_ATM_CASH_BOX = '4';
    char K_L_CASH_BOX = '5';
    char K_R_CASH_BOX = '6';
    char K_VB_BOX_FLAG = '0';
    char K_CASH_BOX_FLAG = '0';
    char K_BV_BOX_FLAG = '1';
    String CPN_R_STARTBR_TLVB = "BP-R-STARTBR-TLVB";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN  ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPOT2901_WS_OUTPUT_DETAIL WS_OUTPUT_DETAIL = new BPOT2901_WS_OUTPUT_DETAIL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPRTLVB BPRTLVB = new BPRTLVB();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRTLVB BPCRTLVB = new BPCRTLVB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    SCCGWA SCCGWA;
    BPB2901_AWA_2901 BPB2901_AWA_2901;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT2901 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB2901_AWA_2901>");
        BPB2901_AWA_2901 = (BPB2901_AWA_2901) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_GET_CASH_BOX();
        if (pgmRtn) return;
        B020_GET_BV_BOX();
        if (pgmRtn) return;
    }
    public void B010_GET_CASH_BOX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTLVB);
        IBS.init(SCCGWA, BPCRTLVB);
        BPRTLVB.KEY.BR = BPB2901_AWA_2901.BR;
        BPRTLVB.KEY.PLBOX_NO = " ";
        BPCRTLVB.INFO.FUNC = 'S';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        BPCRTLVB.INFO.FUNC = 'N';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
        B100_OUTPUT_TITLE();
        if (pgmRtn) return;
        while (BPCRTLVB.RETURN_INFO != 'N') {
            if (BPRTLVB.PLBOX_TP != K_F_CASH_LIB 
                && BPRTLVB.PLBOX_TP != K_Z_CASH_LIB 
                && BPRTLVB.PLBOX_TP != K_ATM_CASH_BOX 
                && BPRTLVB.PLBOX_TP != K_L_CASH_BOX 
                && BPRTLVB.PLBOX_TP != K_R_CASH_BOX) {
                R000_TRANS_DATA_PARM_CASH();
                if (pgmRtn) return;
                B110_OUTPUT_DETAIL();
                if (pgmRtn) return;
            }
            BPCRTLVB.INFO.FUNC = 'N';
            BPCRTLVB.INFO.POINTER = BPRTLVB;
            BPCRTLVB.INFO.LEN = 187;
            S000_CALL_BPZRTLVB();
            if (pgmRtn) return;
        }
        BPCRTLVB.INFO.FUNC = 'E';
        BPCRTLVB.INFO.LEN = 187;
        BPCRTLVB.INFO.POINTER = BPRTLVB;
        S000_CALL_BPZRTLVB();
        if (pgmRtn) return;
    }
    public void B020_GET_BV_BOX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        IBS.init(SCCGWA, BPCRVHPB);
        BPRVHPB.BR = BPB2901_AWA_2901.BR;
        BPRVHPB.POLL_BOX_IND = K_VB_BOX_FLAG;
        CEP.TRC(SCCGWA, BPRVHPB.BR);
        CEP.TRC(SCCGWA, BPRVHPB.POLL_BOX_IND);
        BPCRVHPB.INFO.FUNC = 'I';
        BPCRVHPB.INFO.LEN = 152;
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        BPCRVHPB.INFO.FUNC = 'N';
        BPCRVHPB.INFO.LEN = 152;
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
        while (BPCRVHPB.RETURN_INFO != 'N') {
            R000_TRANS_DATA_PARM_BV();
            if (pgmRtn) return;
            B110_OUTPUT_DETAIL();
            if (pgmRtn) return;
            BPCRVHPB.INFO.FUNC = 'N';
            BPCRVHPB.INFO.LEN = 152;
            BPCRVHPB.INFO.POINTER = BPRVHPB;
            S000_CALL_BPZRVHPB();
            if (pgmRtn) return;
        }
        BPCRVHPB.INFO.FUNC = 'E';
        BPCRVHPB.INFO.LEN = 152;
        BPCRVHPB.INFO.POINTER = BPRVHPB;
        S000_CALL_BPZRVHPB();
        if (pgmRtn) return;
    }
    public void R000_TRANS_DATA_PARM_CASH() throws IOException,SQLException,Exception {
        WS_OUTPUT_DETAIL.WS_OUTPUT_BOX_TYPE = K_CASH_BOX_FLAG;
        WS_OUTPUT_DETAIL.WS_OUTPUT_BOX_NO = BPRTLVB.KEY.PLBOX_NO;
        WS_OUTPUT_DETAIL.WS_OUTPUT_CRNT_TLR = BPRTLVB.CRNT_TLR;
    }
    public void R000_TRANS_DATA_PARM_BV() throws IOException,SQLException,Exception {
        WS_OUTPUT_DETAIL.WS_OUTPUT_BOX_TYPE = K_BV_BOX_FLAG;
        WS_OUTPUT_DETAIL.WS_OUTPUT_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
        WS_OUTPUT_DETAIL.WS_OUTPUT_CRNT_TLR = BPRVHPB.CUR_TLR;
    }
    public void B100_OUTPUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 23;
        SCCMPAG.SCR_ROW_CNT = 99;
        SCCMPAG.SCR_COL_CNT = 4;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B110_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUTPUT_DETAIL);
        SCCMPAG.DATA_LEN = 23;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZRTLVB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_TLVB, BPCRTLVB);
    }
    public void S000_CALL_BPZRVHPB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BPTVHPB_MTN, BPCRVHPB);
        if (BPCRVHPB.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRVHPB.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "END");
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
