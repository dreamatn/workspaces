package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4811 {
    String CPN_S_GRP = "BP-S-MGM-GRP     ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSMGRP BPCSMGRP = new BPCSMGRP();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4810_AWA_4810 BPB4810_AWA_4810;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4811 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4810_AWA_4810>");
        BPB4810_AWA_4810 = (BPB4810_AWA_4810) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSMGRP);
        BPCSMGRP.FUNC = 'A';
        BPCSMGRP.ROLE_CD = BPB4810_AWA_4810.ROLE_CD;
        BPCSMGRP.NAME_CHN = BPB4810_AWA_4810.NAME_C;
        BPCSMGRP.NAME_ENG = BPB4810_AWA_4810.NAME_E;
        BPCSMGRP.ABBR_NAME_CHN = BPB4810_AWA_4810.ABNAME_C;
        BPCSMGRP.ABBR_NAME_ENG = BPB4810_AWA_4810.ABNAME_E;
        BPCSMGRP.AUTH_MOVE_FLG = BPB4810_AWA_4810.MOVE_FLG;
        BPCSMGRP.EFF_DATE = BPB4810_AWA_4810.EFF_DT;
        BPCSMGRP.EXP_DATE = BPB4810_AWA_4810.EXP_DT;
        BPCSMGRP.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSMGRP.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S00_CALL_BPZSMGRP();
    }
    public void S00_CALL_BPZSMGRP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_GRP, BPCSMGRP);
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
