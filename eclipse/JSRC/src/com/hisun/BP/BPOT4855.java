package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4855 {
    String CPN_S_TATH = "BP-S-MGM-TATH    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMTAT BPCSMTAT = new BPCSMTAT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4850_AWA_4850 BPB4850_AWA_4850;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4855 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4850_AWA_4850>");
        BPB4850_AWA_4850 = (BPB4850_AWA_4850) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_UPD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_UPD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMTAT);
        BPCSMTAT.FUNC = 'U';
        BPCSMTAT.MODIFY_FUNC = 'M';
        BPCSMTAT.ASS_TYP = BPB4850_AWA_4850.ASS_TYP;
        BPCSMTAT.ASS_ID = BPB4850_AWA_4850.ASS_ID;
        BPCSMTAT.AUTH_TYP = '1';
        BPCSMTAT.TXIF_NUM = BPB4850_AWA_4850.TXIF_NO;
        BPCSMTAT.IN_FLG = BPB4850_AWA_4850.IN_FLG;
        BPCSMTAT.SYS_MMO = BPB4850_AWA_4850.SYS_MMO;
        BPCSMTAT.TX_CD = BPB4850_AWA_4850.TX_CD;
        BPCSMTAT.MOV_FLG = BPB4850_AWA_4850.MOV_FLG;
        BPCSMTAT.EFF_DATE = BPB4850_AWA_4850.EFF_DT;
        BPCSMTAT.EXP_DATE = BPB4850_AWA_4850.EXP_DT;
        BPCSMTAT.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSMTAT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S00_CALL_BPZSMTAT();
    }
    public void S00_CALL_BPZSMTAT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_TATH, BPCSMTAT);
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
