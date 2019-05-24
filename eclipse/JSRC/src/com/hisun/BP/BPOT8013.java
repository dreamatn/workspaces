package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8013 {
    String K_MMO = "BP";
    String K_OUTPUT_FMT = "BP802";
    String K_CALL_BPZQFLTF = "BP-INQ-FLT-STS-FMT  ";
    String CPN_CALL_BPZPQPCD = "BP-P-INQ-PC     ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCQFLTF BPCQFLTF = new BPCQFLTF();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    SCCGWA SCCGWA;
    BPB8013_AWA_8013 BPB8013_AWA_8013;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8013 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8013_AWA_8013>");
        BPB8013_AWA_8013 = (BPB8013_AWA_8013) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, BPCQFLTF);
        IBS.init(SCCGWA, BPCOQPCD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_GET_FLT_INFO();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB8013_AWA_8013.FLT_CODE);
        if (BPB8013_AWA_8013.FLT_CODE.trim().length() > 0) {
            BPCOQPCD.INPUT_DATA.TYPE = "FLT-T";
            BPCOQPCD.INPUT_DATA.CODE = BPB8013_AWA_8013.FLT_CODE;
            S000_CALL_BPZPQPCD();
        }
    }
    public void B020_GET_FLT_INFO() throws IOException,SQLException,Exception {
        BPCQFLTF.FMT = K_OUTPUT_FMT;
        BPCQFLTF.FLT_CODE = BPB8013_AWA_8013.FLT_CODE;
        S000_CALL_BPZQFLTF();
    }
    public void S000_CALL_BPZQFLTF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, K_CALL_BPZQFLTF, BPCQFLTF);
    }
    public void S000_CALL_BPZPQPCD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_CALL_BPZPQPCD, BPCOQPCD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
