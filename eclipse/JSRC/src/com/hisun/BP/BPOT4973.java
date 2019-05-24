package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4973 {
    String CPN_S_TMUP = "BP-S-TEMP-PRO    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSTMUP BPCSTMUP = new BPCSTMUP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4970_AWA_4970 BPB4970_AWA_4970;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4973 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4970_AWA_4970>");
        BPB4970_AWA_4970 = (BPB4970_AWA_4970) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_UPGRADE_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_UPGRADE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSTMUP);
        BPCSTMUP.FUNC = 'U';
        BPCSTMUP.TLR_OUT = BPB4970_AWA_4970.TLR_OUT;
        BPCSTMUP.TLR_IN = BPB4970_AWA_4970.TLR_IN;
        BPCSTMUP.MOV_DATE = BPB4970_AWA_4970.MOV_DT;
        BPCSTMUP.MOV_JRN_NO = BPB4970_AWA_4970.VCH_NO;
        BPCSTMUP.PRIV_TYP = BPB4970_AWA_4970.PRIV_TYP;
        BPCSTMUP.ATTR = BPB4970_AWA_4970.ATTR;
        BPCSTMUP.MOV_EXP_DATE = BPB4970_AWA_4970.MOV_EXP;
        BPCSTMUP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSTMUP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S00_CALL_BPZSTMUP();
    }
    public void S00_CALL_BPZSTMUP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TMUP, BPCSTMUP);
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
