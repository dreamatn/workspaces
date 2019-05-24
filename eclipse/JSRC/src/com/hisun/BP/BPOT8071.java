package com.hisun.BP;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8071 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_U_CARD_CINF = "DC-U-CARD-INF";
    String CPN_S_INQ_FHSA = "BP-S-INQ-FHSA";
    String CPN_P_QUE_ACTY = "BP-P-QUERY-AC-TYPE";
    BPOT8071_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT8071_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSIHSA BPCSIHSA = new BPCSIHSA();
    SCCGWA SCCGWA;
    BPB8070_AWA_8070 BPB8070_AWA_8070;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8071 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8070_AWA_8070>");
        BPB8070_AWA_8070 = (BPB8070_AWA_8070) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        IBS.init(SCCGWA, BPCSIHSA);
        B030_BROWSE_HIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB8070_AWA_8070.TX_CCY);
        CEP.TRC(SCCGWA, BPB8070_AWA_8070.STR_DT);
        CEP.TRC(SCCGWA, BPB8070_AWA_8070.END_DT);
        if (BPB8070_AWA_8070.TX_CCY.trim().length() == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8070_AWA_8070.TX_CCY_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB8070_AWA_8070.STR_DT == 0 
            && BPB8070_AWA_8070.END_DT == 0) {
            BPB8070_AWA_8070.STR_DT = SCCGWA.COMM_AREA.AC_DATE;
            BPB8070_AWA_8070.END_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        if (BPB8070_AWA_8070.STR_DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATE_GT_ACDATE;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8070_AWA_8070.STR_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB8070_AWA_8070.END_DT >= SCCGWA.COMM_AREA.AC_DATE) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_ENDDT_MUST_LS_ACDT;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8070_AWA_8070.END_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB8070_AWA_8070.STR_DT > BPB8070_AWA_8070.END_DT) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_GT_END;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8070_AWA_8070.STR_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB8070_AWA_8070.STR_DT != 0 
            && BPB8070_AWA_8070.END_DT == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8070_AWA_8070.END_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB8070_AWA_8070.END_DT != 0 
            && BPB8070_AWA_8070.STR_DT == 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8070_AWA_8070.STR_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        CEP.TRC(SCCGWA, "AAA");
        WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_ERROR;
        WS_TEMP_VARIABLE.WS_FLD_NO = 0;
        S000_ERR_MSG_PROC_LAST();
        CEP.TRC(SCCGWA, "BBB");
    }
    public void B030_BROWSE_HIST() throws IOException,SQLException,Exception {
        BPCSIHSA.DATA.AC = BPB8070_AWA_8070.AC;
        BPCSIHSA.DATA.CCY = BPB8070_AWA_8070.TX_CCY;
        BPCSIHSA.DATA.STR_AC_DT = BPB8070_AWA_8070.STR_DT;
        BPCSIHSA.DATA.END_AC_DT = BPB8070_AWA_8070.END_DT;
        S000_CALL_BPZSIHSA();
    }
    public void S000_CALL_BPZSIHSA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_INQ_FHSA, BPCSIHSA);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSIHSA.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSIHSA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_LAST() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_ERR_MSG, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
