package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT1266 {
    String JIBS_tmp_str[] = new String[10];
    short K_MAX_ROW_CNT = 50;
    BPOT1266_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT1266_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    BPCPORUP BPCPORUP = new BPCPORUP();
    BPCBFPDT BPCBFPDT = new BPCBFPDT();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPB1266_AWA_1266 BPB1266_AWA_1266;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT1266 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB1266_AWA_1266>");
        BPB1266_AWA_1266 = (BPB1266_AWA_1266) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        B200_BROW_FPDT_PROC();
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB1266_AWA_1266.BSNS_NO.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (BPB1266_AWA_1266.PAGE_NUM == 0) {
            BPB1266_AWA_1266.PAGE_NUM = 1;
        }
        if (BPB1266_AWA_1266.PAGE_ROW == 0 
            || BPB1266_AWA_1266.PAGE_ROW > K_MAX_ROW_CNT) {
            BPB1266_AWA_1266.PAGE_ROW = K_MAX_ROW_CNT;
        }
    }
    public void B200_BROW_FPDT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCBFPDT);
        R000_MOVE_DATA();
        S000_CALL_BPZBFPDT();
    }
    public void R000_MOVE_DATA() throws IOException,SQLException,Exception {
        BPCBFPDT.BSNS_NO = BPB1266_AWA_1266.BSNS_NO;
        BPCBFPDT.FEE_CODE = BPB1266_AWA_1266.FEE_CODE;
        BPCBFPDT.PAGE_NUM = BPB1266_AWA_1266.PAGE_NUM;
        BPCBFPDT.PAGE_ROW = BPB1266_AWA_1266.PAGE_ROW;
    }
    public void S000_CALL_BPZBFPDT() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-FPDT-BROW", BPCBFPDT);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
