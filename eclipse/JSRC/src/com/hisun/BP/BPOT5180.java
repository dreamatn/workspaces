package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT5180 {
    String CPN_GEN_TQP_MIDR = "BP-GEN-TQP-MIDR    ";
    String CPN_GEN_TQP_MKTR = "BP-GEN-TQP-MKTR    ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_SET_SUB_TXN = "SC-SET-SUBS-TRANS ";
    String WS_CCY = " ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPREXRT BPREXRT = new BPREXRT();
    BPCQCCY BPCQCCY = new BPCQCCY();
    SCCSUBS SCCSUBS = new SCCSUBS();
    BPCGTI BPCGTI = new BPCGTI();
    BPCGTK BPCGTK = new BPCGTK();
    BPRPRMT BPRPRMT = new BPRPRMT();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB5180_AWA_5180 BPB5180_AWA_5180;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT5180 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB5180_AWA_5180>");
        BPB5180_AWA_5180 = (BPB5180_AWA_5180) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_BRW_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
    }
    public void B020_BRW_PROC() throws IOException,SQLException,Exception {
        if (BPB5180_AWA_5180.BASE_TYP.equalsIgnoreCase("01") 
            || BPB5180_AWA_5180.BASE_TYP.equalsIgnoreCase("02")) {
            IBS.init(SCCGWA, BPCGTI);
            BPCGTI.DATA.FUNC = 'B';
            BPCGTI.DATA.EXR_TYPE = BPB5180_AWA_5180.EXR_TYPE;
            BPCGTI.DATA.TENOR = BPB5180_AWA_5180.FWD_TENR;
            BPCGTI.DATA.BASE_TYPE = BPB5180_AWA_5180.BASE_TYP;
            BPCGTI.DATA.BASE_CCY = BPB5180_AWA_5180.BASE_CCY;
            S00_CALL_BPZGTI();
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 0;
            if ("5181".trim().length() == 0) SCCSUBS.TR_CODE = 0;
            else SCCSUBS.TR_CODE = Short.parseShort("5181");
            S000_CALL_SCZSUBS();
        } else {
            IBS.init(SCCGWA, BPCGTK);
            BPCGTK.DATA.FUNC = 'B';
            BPCGTK.DATA.EXR_TYPE = BPB5180_AWA_5180.EXR_TYPE;
            BPCGTK.DATA.TENOR = BPB5180_AWA_5180.FWD_TENR;
            BPCGTK.DATA.BASE_TYPE = BPB5180_AWA_5180.BASE_TYP;
            BPCGTK.DATA.BASE_CCY = BPB5180_AWA_5180.BASE_CCY;
            S00_CALL_BPZGTK();
            IBS.init(SCCGWA, SCCSUBS);
            SCCSUBS.AP_CODE = 0;
            if ("5182".trim().length() == 0) SCCSUBS.TR_CODE = 0;
            else SCCSUBS.TR_CODE = Short.parseShort("5182");
            S000_CALL_SCZSUBS();
        }
    }
    public void S00_CALL_BPZGTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GEN_TQP_MIDR, BPCGTI);
    }
    public void S00_CALL_BPZGTK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_GEN_TQP_MKTR, BPCGTK);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPRPRMT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void S000_CALL_SCZSUBS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_SET_SUB_TXN, SCCSUBS);
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
