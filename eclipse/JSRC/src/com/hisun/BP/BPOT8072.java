package com.hisun.BP;

import com.hisun.DC.*;
import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8072 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_U_CARD_CINF = "DC-U-CARD-INF";
    String CPN_P_QUE_ACTY = "DC-INQ-AC-INF";
    String CPN_S_INQ_GBAL = "BP-S-INQ-GBAL";
    BPOT8072_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT8072_WS_TEMP_VARIABLE();
    char WS_AC_FLG1 = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSGBAL BPCSGBAL = new BPCSGBAL();
    BPCIPHIS BPCIPHIS = new BPCIPHIS();
    DCCUCINF DCCUCINF = new DCCUCINF();
    DCCPACTY DCCPACTY = new DCCPACTY();
    SCCGWA SCCGWA;
    BPB8070_AWA_8070 BPB8070_AWA_8070;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPOT8072 return!");
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
        B030_GETBAL_FROM_HIST();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPB8070_AWA_8070.STR_DT);
        CEP.TRC(SCCGWA, BPB8070_AWA_8070.END_DT);
        CEP.TRC(SCCGWA, BPB8070_AWA_8070.AC);
        CEP.TRC(SCCGWA, BPB8070_AWA_8070.TX_CCY);
        if (BPB8070_AWA_8070.STR_DT > BPB8070_AWA_8070.END_DT) {
            CEP.TRC(SCCGWA, "ERRMSG:M-BP-STR-GT-END");
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_STR_GT_END;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8070_AWA_8070.STR_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB8070_AWA_8070.STR_DT != 0 
            && BPB8070_AWA_8070.END_DT == 0) {
            CEP.TRC(SCCGWA, "ERRMSG:M-BP-INPUT-SAME-TIME");
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8070_AWA_8070.END_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        if (BPB8070_AWA_8070.END_DT != 0 
            && BPB8070_AWA_8070.STR_DT == 0) {
            CEP.TRC(SCCGWA, "ERRMSG:M-BP-INPUT-SAME-TIME");
            WS_TEMP_VARIABLE.WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_INPUT_SAME_TIME;
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8070_AWA_8070.STR_DT_NO;
            S000_ERR_MSG_PROC_CONTINUE();
        }
        IBS.init(SCCGWA, DCCPACTY);
        DCCPACTY.INPUT.AC = BPB8070_AWA_8070.AC;
        DCCPACTY.INPUT.CCY = BPB8070_AWA_8070.TX_CCY;
        S000_CALL_DCZPACTY();
        CEP.TRC(SCCGWA, DCCPACTY.OUTPUT.STD_AC);
        WS_AC_FLG1 = DCCPACTY.OUTPUT.AC_TYPE;
        CEP.TRC(SCCGWA, WS_AC_FLG1);
        if (WS_AC_FLG1 == 'D' 
            && DCCPACTY.OUTPUT.AC_STD_FLG != '0' 
            && !DCCPACTY.OUTPUT.AC_FREE_TYPE.equalsIgnoreCase("004")) {
            BPB8070_AWA_8070.AC = DCCPACTY.OUTPUT.STD_AC;
            WS_TEMP_VARIABLE.WS_TX_TOOL = " ";
        } else {
            WS_TEMP_VARIABLE.WS_TX_TOOL = BPB8070_AWA_8070.AC;
            BPB8070_AWA_8070.AC = " ";
        }
        CEP.TRC(SCCGWA, BPB8070_AWA_8070.AC);
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_TX_TOOL);
        CEP.TRC(SCCGWA, "AAA");
        S000_ERR_MSG_PROC_LAST();
        CEP.TRC(SCCGWA, "BBB");
    }
    public void B030_GETBAL_FROM_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGBAL);
        BPCSGBAL.INPUT.AC = BPB8070_AWA_8070.AC;
        BPCSGBAL.INPUT.TX_TOOL = WS_TEMP_VARIABLE.WS_TX_TOOL;
        BPCSGBAL.INPUT.TX_CCY = BPB8070_AWA_8070.TX_CCY;
        BPCSGBAL.INPUT.STR_DT = BPB8070_AWA_8070.STR_DT;
        BPCSGBAL.INPUT.END_DT = BPB8070_AWA_8070.END_DT;
        BPCSGBAL.INPUT.DC_FLG = BPB8070_AWA_8070.DC_FLG;
        S000_CALL_BPZSGBAL();
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_U_CARD_CINF, DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_DCZPACTY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_QUE_ACTY, DCCPACTY);
        if (DCCPACTY.RC.RC_CODE != 0) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCPACTY.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSGBAL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_S_INQ_GBAL, BPCSGBAL);
        CEP.TRC(SCCGWA, BPCSGBAL.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSGBAL.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            WS_TEMP_VARIABLE.WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGBAL.RC);
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
