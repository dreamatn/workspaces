package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4844 {
    String CPN_S_GRPL = "BP-S-MGM-GRPL    ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRGRPL BPRGRPL = new BPRGRPL();
    BPCSMGRL BPCSMGRL = new BPCSMGRL();
    BPCRGRPL BPCRGRPL = new BPCRGRPL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4840_AWA_4840 BPB4840_AWA_4840;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4844 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4840_AWA_4840>");
        BPB4840_AWA_4840 = (BPB4840_AWA_4840) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
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
        IBS.init(SCCGWA, BPCSMGRL);
        IBS.init(SCCGWA, BPCRGRPL);
        IBS.init(SCCGWA, BPRGRPL);
        BPRGRPL.KEY.ASS_TYP = BPB4840_AWA_4840.ASS_TYP;
        BPRGRPL.KEY.ASS_ID = BPB4840_AWA_4840.ASS_ID;
        BPRGRPL.KEY.ATH_TYP = '1';
        BPCRGRPL.INFO.FUNC = 'Q';
        S000_CALL_BPZRGRPL();
        CEP.TRC(SCCGWA, BPCRGRPL.RETURN_INFO);
        if (BPCRGRPL.RETURN_INFO == 'F') {
            BPCSMGRL.FUNC = 'U';
            BPCSMGRL.MODIFY_FUNC = 'A';
        } else {
            BPCSMGRL.FUNC = 'A';
            BPCSMGRL.ROLE_NUM = 1;
        }
        BPCSMGRL.ASS_TYP = BPB4840_AWA_4840.ASS_TYP;
        BPCSMGRL.ASS_ID = BPB4840_AWA_4840.ASS_ID;
        BPCSMGRL.AUTH_TYP = '1';
        BPCSMGRL.ROLE_CD = BPB4840_AWA_4840.ROLE_CD;
        BPCSMGRL.MOV_FLG = BPB4840_AWA_4840.MOV_FLG;
        BPCSMGRL.EFF_DATE = BPB4840_AWA_4840.EFF_DT;
        BPCSMGRL.EXP_DATE = BPB4840_AWA_4840.EXP_DT;
        BPCSMGRL.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSMGRL.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        S00_CALL_BPZSMGRL();
    }
    public void S00_CALL_BPZSMGRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-MGM-GRPL    ", BPCSMGRL);
    }
    public void S000_CALL_BPZRGRPL() throws IOException,SQLException,Exception {
        BPCRGRPL.INFO.POINTER = BPRGRPL;
        BPCRGRPL.INFO.LEN = 52;
        IBS.CALLCPN(SCCGWA, "BP-R-MGM-GRPL", BPCRGRPL);
        if (BPCRGRPL.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRGRPL.RC);
            S000_ERR_MSG_PROC();
        }
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
