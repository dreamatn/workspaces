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

public class BPZSBLBV {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP175";
    String K_HIS_REMARKS = "BOX-RENDER";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_F_K_PSW = "BP-F-K-PSW-MAINTAIN ";
    String CPN_F_C_PSW = "BP-F-C-PSW-MAINTAIN ";
    String CPN_U_BV_OUT = "BP-U-TLR-BV-OUT     ";
    String CPN_U_BV_IN = "BP-U-TLR-BV-IN      ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCO175 BPCO175 = new BPCO175();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFKPSW BPCFKPSW = new BPCFKPSW();
    BPCFCPSW BPCFCPSW = new BPCFCPSW();
    BPCUBVOU BPCUBVOU = new BPCUBVOU();
    BPCUBVIN BPCUBVIN = new BPCUBVIN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSBLBV BPCSBLBV;
    public void MP(SCCGWA SCCGWA, BPCSBLBV BPCSBLBV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBLBV = BPCSBLBV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBLBV return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHK_INPUT();
        if (pgmRtn) return;
        B020_TLR_BV_OUT();
        if (pgmRtn) return;
        B030_TLR_BV_IN();
        if (pgmRtn) return;
        B050_REC_NHIS();
        if (pgmRtn) return;
        B060_DATA_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCSBLBV.RCV_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        if (BPCFTLRQ.INFO.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_NOTMATCH_ORG;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.SIGN_STS);
        if (BPCFTLRQ.INFO.SIGN_STS != 'O' 
            && BPCFTLRQ.INFO.SIGN_STS != 'T') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_TLR_MUST_SIGN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSBLBV.PSW_TYP);
        CEP.TRC(SCCGWA, BPCSBLBV.TLRK_PSW);
        CEP.TRC(SCCGWA, BPCSBLBV.TLRC_PSW);
        if (BPCSBLBV.PSW_TYP == '1') {
            IBS.init(SCCGWA, BPCFCPSW);
            BPCFCPSW.OPT = 'C';
            BPCFCPSW.TLR = BPCSBLBV.RCV_TLR;
            BPCFCPSW.OLD_PSW = BPCSBLBV.TLRC_PSW;
            S000_CALL_BPZFCPSW();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCFKPSW);
            BPCFKPSW.OPT = 'C';
            BPCFKPSW.TLR = BPCSBLBV.RCV_TLR;
            BPCFKPSW.OLD_PSW = BPCSBLBV.TLRK_PSW;
            S000_CALL_BPZFKPSW();
            if (pgmRtn) return;
        }
    }
    public void B020_TLR_BV_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBVOU);
        BPCUBVOU.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBVOU.TYPE = '1';
        BPCUBVOU.BV_CODE = BPCSBLBV.BV_CODE;
        BPCUBVOU.CCY = BPCSBLBV.CCY;
        BPCUBVOU.PVAL = BPCSBLBV.VALUE;
        BPCUBVOU.HEAD_NO = BPCSBLBV.HEAD_NO;
        BPCUBVOU.BEG_NO = BPCSBLBV.BEG_NO;
        BPCUBVOU.END_NO = BPCSBLBV.END_NO;
        BPCUBVOU.NUM = BPCSBLBV.NUM;
        BPCUBVOU.AMT = BPCSBLBV.AMT;
        BPCUBVOU.VB_FLG = '0';
        BPCUBVOU.BV_STS = '0';
        BPCUBVOU.AC_TYP = '1';
        CEP.TRC(SCCGWA, BPCUBVOU.VB_FLG);
        S000_CALL_BPZUBVOU();
        if (pgmRtn) return;
    }
    public void B030_TLR_BV_IN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCUBVIN);
        BPCUBVIN.TYPE = '1';
        BPCUBVIN.TLR = BPCSBLBV.RCV_TLR;
        BPCUBVIN.BV_CODE = BPCSBLBV.BV_CODE;
        BPCUBVIN.CCY = BPCSBLBV.CCY;
        BPCUBVIN.PVAL = BPCSBLBV.VALUE;
        BPCUBVIN.HEAD_NO = BPCSBLBV.HEAD_NO;
        BPCUBVIN.BEG_NO = BPCSBLBV.BEG_NO;
        BPCUBVIN.END_NO = BPCSBLBV.END_NO;
        BPCUBVIN.NUM = BPCSBLBV.NUM;
        BPCUBVIN.AMT = BPCSBLBV.AMT;
        BPCUBVIN.VB_FLG = '1';
        BPCUBVIN.BV_STS = '0';
        BPCUBVIN.AC_TYP = '1';
        S000_CALL_BPZUBVIN();
        if (pgmRtn) return;
    }
    public void B050_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "01";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = K_HIS_REMARKS;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R010_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO175;
