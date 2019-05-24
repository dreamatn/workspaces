package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT6005 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    char K_ERROR = 'E';
    BPOT6005_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT6005_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPPDMM BPRPPDMM = new BPRPPDMM();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCO700 BPCO700 = new BPCO700();
    SCCGWA SCCGWA;
    BPB6000_AWA_6000 BPB6000_AWA_6000;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT6005 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB6000_AWA_6000>");
        BPB6000_AWA_6000 = (BPB6000_AWA_6000) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_QUERY_PROCESS();
        B200_OUT_PROCESS();
    }
    public void B100_QUERY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, BPRPRMT);
        IBS.init(SCCGWA, BPRPPDMM);
        if (BPB6000_AWA_6000.PRDT_MOD.trim().length() == 0) {
        } else {
            BPRPRMT.KEY.CD = BPB6000_AWA_6000.PRDT_MOD;
            BPRPRMT.KEY.TYP = "PRDM ";
            BPCPRMR.DAT_PTR = BPRPRMT;
            S000_CALL_BPZPRMR();
            CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
            CEP.TRC(SCCGWA, BPCPRMR.RC);
            if (BPCPRMR.RC.RC_RTNCODE == 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRPRMT);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPRPPDMM);
            } else {
                WS_TEMP_VARIABLE.WS_MSGID = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
                S000_ERR_MSG_PROC();
            }
        }
    }
    public void B200_OUT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPPDMM.DATA_TXT.DESC_MODEL);
        IBS.init(SCCGWA, BPCO700);
        IBS.init(SCCGWA, SCCFMT);
        BPCO700.PARM_TYPE = BPRPPDMM.KEY.TYP;
        if (BPRPPDMM.KEY.CD == null) BPRPPDMM.KEY.CD = "";
        JIBS_tmp_int = BPRPPDMM.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPDMM.KEY.CD += " ";
        BPCO700.PRDT_MODEL = BPRPPDMM.KEY.CD.substring(0, 3);
        BPCO700.ENG_DESC = BPRPPDMM.DESC;
        BPCO700.CHN_DESC = BPRPPDMM.CDESC;
        BPCO700.FLAG_01 = 0X02;
        BPCO700.PARM_TX = BPRPPDMM.DATA_TXT.PARM_TX;
        BPCO700.CREATE_TX = BPRPPDMM.DATA_TXT.CREATE_TX;
        BPCO700.INNER_PRDT_IND = BPRPPDMM.DATA_TXT.INNER_PRDT_IND;
        BPCO700.DEFT_PRDT = BPRPPDMM.DATA_TXT.DEFT_PRDT;
        BPCO700.OPEN_DATE = BPRPPDMM.DATA_TXT.OPEN_DATE;
        BPCO700.LAST_DATE = BPRPPDMM.DATA_TXT.LAST_DATE;
        BPCO700.LAST_TLR = BPRPPDMM.DATA_TXT.LAST_TLR;
        BPCO700.DESC = BPRPPDMM.DATA_TXT.DESC_MODEL;
        BPCO700.FLAG_02 = 0X02;
        SCCFMT.FMTID = "BP700";
        SCCFMT.DATA_PTR = BPCO700;
        SCCFMT.DATA_LEN = 282;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ        ", BPCPRMR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        CEP.ERRC(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID, WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
