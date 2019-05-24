package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT4321 {
    int JIBS_tmp_int;
    String CPN_S_EXRT = "BP-DEF-EXRT      ";
    String CPN_INQUIRE_CCY = "BP-INQUIRE-CCY    ";
    String CPN_INQ_PUB_PARM = "BP-PARM-READ      ";
    String CPN_INQ_PUB_CODE = "BP-P-INQ-PC       ";
    String K_PARM_EXR_TYP = "EXRT";
    String K_FWD_TENOR = "FWDT ";
    String CPN_INQ_EXR_CODE = "BP-INQ-EXR-CODE   ";
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
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCDERT BPCDERT = new BPCDERT();
    BPCOIEC BPCOIEC = new BPCOIEC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPB4320_AWA_4320 BPB4320_AWA_4320;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT4321 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB4320_AWA_4320>");
        BPB4320_AWA_4320 = (BPB4320_AWA_4320) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_ADD_REC_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        WS_CCY = BPB4320_AWA_4320.CCY;
        WS_FLD_NO = BPB4320_AWA_4320.CCY_NO;
        R000_CHECK_CCY();
        IBS.init(SCCGWA, BPREXRT);
        IBS.init(SCCGWA, BPCPRMR);
        BPREXRT.KEY.TYP = "EXRT";
        BPREXRT.KEY.CD = BPB4320_AWA_4320.EXR_TYPE;
        S000_CALL_BPZPRMR();
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_INVALID_EXR_TYP);
        }
        CEP.TRC(SCCGWA, BPREXRT.DAT_TXT.BASE_CCY);
        IBS.init(SCCGWA, BPCOIEC);
        BPCOIEC.CCY1 = BPB4320_AWA_4320.CCY;
        BPCOIEC.CCY2 = BPREXRT.DAT_TXT.BASE_CCY;
        BPCOIEC.EXR_TYP = BPB4320_AWA_4320.EXR_TYPE;
        S000_CALL_BPZSIEC();
    }
    public void S000_CALL_BPZSIEC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_INQ_EXR_CODE, BPCOIEC);
        CEP.TRC(SCCGWA, "DEVHZ235");
        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
        if (BPCOIEC.EXR_CODE == null) BPCOIEC.EXR_CODE = "";
        JIBS_tmp_int = BPCOIEC.EXR_CODE.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCOIEC.EXR_CODE += " ";
        if (BPCOIEC.EXR_CODE.substring(0, 3).trim().length() == 0 
            || BPCOIEC.EXR_CODE.substring(4 - 1, 4 + 3 - 1).trim().length() == 0) {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_NOT_DEF_EX_CCY);
        }
        CEP.TRC(SCCGWA, BPCOIEC.RC);
        if (BPCOIEC.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCOIEC.RC);
        }
    }
    public void B020_ADD_REC_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "DEVHZ253");
        IBS.init(SCCGWA, BPCDERT);
        BPCDERT.FUNC = 'A';
        BPCDERT.EXR_TYP = BPB4320_AWA_4320.EXR_TYPE;
        BPCDERT.CCY = BPB4320_AWA_4320.CCY;
        BPCDERT.UNT = BPB4320_AWA_4320.EX_UNIT;
        BPCDERT.METH = BPB4320_AWA_4320.FXSP_MTH;
        BPCDERT.EXR_PNT = BPB4320_AWA_4320.EX_POINT;
        BPCDERT.EXR_RND = BPB4320_AWA_4320.RND_MTH;
        BPCDERT.PNL_SQN = BPB4320_AWA_4320.SEQ;
        BPCDERT.RMK = BPB4320_AWA_4320.REMARK;
        S00_CALL_BPZDERT();
    }
    public void S00_CALL_BPZDERT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_EXRT, BPCDERT);
        if (BPCDERT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCDERT.RC);
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPREXRT;
        IBS.CALLCPN(SCCGWA, CPN_INQ_PUB_PARM, BPCPRMR);
    }
    public void R000_CHECK_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCQCCY);
        BPCQCCY.DATA.CCY = WS_CCY;
        IBS.CALLCPN(SCCGWA, CPN_INQUIRE_CCY, BPCQCCY);
        if (BPCQCCY.RC.RTNCODE != 0) {
            CEP.ERR(SCCGWA, BPCQCCY.RC);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
