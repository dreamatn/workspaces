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

public class BPZSBVBT {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP162";
    String CPN_R_MGM_BUSE = "BP-R-MGM-BUSE       ";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY   ";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    String CPN_S_TLR_INFO_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_R_BPTVHPB_MTN = "BP-R-BPTVHPB-MTN";
    String CPN_U_BV_OUT = "BP-U-TLR-BV-OUT     ";
    String CPN_U_BV_IN = "BP-U-TLR-BV-IN      ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    int K_CNT = 10;
    String WS_ERR_MSG = " ";
    String WS_TEMP_PLBOX_NO = " ";
    String BPZSBVBT_FILLER1 = "BV CODE:";
    String WS_HIS_BVCODE = " ";
    String BPZSBVBT_FILLER3 = "HEAD NO:";
    String WS_HIS_HEADNO = " ";
    String BPZSBVBT_FILLER5 = "BEG NO:";
    String WS_HIS_BEGNO = " ";
    String BPZSBVBT_FILLER7 = "END NO:";
    String WS_HIS_ENDNO = " ";
    String BPZSBVBT_FILLER9 = "NUM NO:";
    int WS_HIS_NUMNO = 0;
    BPZSBVBT_WS_TYPE_INFO[] WS_TYPE_INFO = new BPZSBVBT_WS_TYPE_INFO[10];
    int WS_I = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBMOV BPRBMOV = new BPRBMOV();
    BPRBUSE BPRBUSE = new BPRBUSE();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCRBUSE BPCRBUSE = new BPCRBUSE();
    BPCO155 BPCO155 = new BPCO155();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPCRVHPB BPCRVHPB = new BPCRVHPB();
    BPCUBVOU BPCUBVOU = new BPCUBVOU();
    BPCUBVIN BPCUBVIN = new BPCUBVIN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCSBVBT BPCSBVBT;
    public BPZSBVBT() {
        for (int i=0;i<10;i++) WS_TYPE_INFO[i] = new BPZSBVBT_WS_TYPE_INFO();
    }
    public void MP(SCCGWA SCCGWA, BPCSBVBT BPCSBVBT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSBVBT = BPCSBVBT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSBVBT return!");
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
        if (BPCSBVBT.MOV_FLG != 'Y') {
            B060_DATA_OUTPUT();
            if (pgmRtn) return;
        }
    }
    public void B010_CHK_INPUT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.TL_ID.equalsIgnoreCase(BPCSBVBT.RCV_TLR)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CANNOT_SAME_INPTL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCSBVBT.RCV_TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BBOX_TLR;
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
        for (WS_I = 1; WS_I <= K_CNT 
            && BPCSBVBT.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, BPCFBVQU);
            BPCFBVQU.TX_DATA.KEY.CODE = BPCSBVBT.BV_DATA[WS_I-1].BV_CODE;
            S000_CALL_BPZFBVQU();
            if (pgmRtn) return;
            WS_TYPE_INFO[WS_I-1].WS_BV_TYPE = BPCFBVQU.TX_DATA.TYPE;
        }
    }
    public void B020_TLR_BV_OUT() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= K_CNT 
            && BPCSBVBT.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, BPCUBVOU);
            BPCUBVOU.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUBVOU.TYPE = WS_TYPE_INFO[WS_I-1].WS_BV_TYPE;
            BPCUBVOU.BV_CODE = BPCSBVBT.BV_DATA[WS_I-1].BV_CODE;
            BPCUBVOU.HEAD_NO = BPCSBVBT.BV_DATA[WS_I-1].HEAD_NO;
            BPCUBVOU.BEG_NO = BPCSBVBT.BV_DATA[WS_I-1].BEG_NO;
            BPCUBVOU.END_NO = BPCSBVBT.BV_DATA[WS_I-1].END_NO;
            BPCUBVOU.NUM = BPCSBVBT.BV_DATA[WS_I-1].NUM;
            BPCUBVOU.BV_STS = BPCSBVBT.BV_DATA[WS_I-1].BV_STS;
            BPCUBVOU.VB_FLG = '0';
            BPCUBVOU.AC_TYP = '1';
            BPCUBVOU.USECTL = 'N';
            S000_CALL_BPZUBVOU();
            if (pgmRtn) return;
        }
    }
    public void B030_TLR_BV_IN() throws IOException,SQLException,Exception {
        for (WS_I = 1; WS_I <= K_CNT 
            && BPCSBVBT.BV_DATA[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            CEP.TRC(SCCGWA, BPCSBVBT.BV_DATA[WS_I-1].BV_CODE);
            IBS.init(SCCGWA, BPCUBVIN);
            BPCUBVIN.TLR = BPCSBVBT.RCV_TLR;
            BPCUBVIN.TYPE = WS_TYPE_INFO[WS_I-1].WS_BV_TYPE;
            BPCUBVIN.BV_CODE = BPCSBVBT.BV_DATA[WS_I-1].BV_CODE;
            BPCUBVIN.HEAD_NO = BPCSBVBT.BV_DATA[WS_I-1].HEAD_NO;
            BPCUBVIN.BEG_NO = BPCSBVBT.BV_DATA[WS_I-1].BEG_NO;
            BPCUBVIN.END_NO = BPCSBVBT.BV_DATA[WS_I-1].END_NO;
            BPCUBVIN.NUM = BPCSBVBT.BV_DATA[WS_I-1].NUM;
            BPCUBVIN.BV_STS = BPCSBVBT.BV_DATA[WS_I-1].BV_STS;
            CEP.TRC(SCCGWA, BPCUBVIN.TYPE);
            BPCUBVIN.VB_FLG = '0';
            CEP.TRC(SCCGWA, BPCUBVIN.VB_FLG);
            BPCUBVIN.AC_TYP = '1';
            S000_CALL_BPZUBVIN();
            if (pgmRtn) return;
        }
    }
    public void B050_REC_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_RMK = IBS.CLS2CPY(SCCGWA, BPZSBVBT_WS2);
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B060_DATA_OUTPUT() throws IOException,SQLException,Exception {
        R010_TRANS_DATA_OUTPUT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = BPCO155;
