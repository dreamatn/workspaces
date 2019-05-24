package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4983 {
    String CPN_S_PRIV = "BP-S-MGM-PRIV    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMPRI BPCSMPRI = new BPCSMPRI();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4861_AWA_4861 BPB4861_AWA_4861;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4983 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4861_AWA_4861>");
        BPB4861_AWA_4861 = (BPB4861_AWA_4861) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_TRANSFER_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_TRANSFER_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMPRI);
        BPCSMPRI.FUNC = 'D';
        BPCSMPRI.TLR_OUT = BPB4861_AWA_4861.TLR_OUT;
        BPCSMPRI.TLR_IN = BPB4861_AWA_4861.TLR_IN;
        BPCSMPRI.PRIV_TYP = BPB4861_AWA_4861.PRIV_TYP;
        BPCSMPRI.TRAN_TYP = BPB4861_AWA_4861.TRAN_TYP;
        BPCSMPRI.IN_FLG = BPB4861_AWA_4861.IN_FLG;
        BPCSMPRI.SYS_MMO = BPB4861_AWA_4861.SYS_MMO;
        BPCSMPRI.TX_CD = BPB4861_AWA_4861.TX_CD;
        BPCSMPRI.ATTR = BPB4861_AWA_4861.ATTR;
        BPCSMPRI.MOV_EXP_DATE = BPB4861_AWA_4861.M_EXP_DT;
        BPCSMPRI.MOV_EFF_DATE = BPB4861_AWA_4861.M_EFF_DT;
        BPCSMPRI.MOV_EXP_TIME = BPB4861_AWA_4861.M_EXP_TM;
        BPCSMPRI.MOV_EFF_TIME = BPB4861_AWA_4861.M_EFF_TM;
        CEP.TRC(SCCGWA, BPCSMPRI);
        CEP.TRC(SCCGWA, BPB4861_AWA_4861.TX_CD);
        CEP.TRC(SCCGWA, BPB4861_AWA_4861.ATTR);
        CEP.TRC(SCCGWA, BPCSMPRI.MOV_EFF_DATE);
        CEP.TRC(SCCGWA, BPCSMPRI.MOV_EXP_DATE);
        CEP.TRC(SCCGWA, BPCSMPRI.MOV_EXP_TIME);
        CEP.TRC(SCCGWA, BPCSMPRI.MOV_EFF_TIME);
        BPCSMPRI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSMPRI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S00_CALL_BPZSMPRI();
    }
    public void S00_CALL_BPZSMPRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_PRIV, BPCSMPRI);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
