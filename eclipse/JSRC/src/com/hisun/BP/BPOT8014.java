package com.hisun.BP;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class BPOT8014 {
    String JIBS_tmp_str[] = new String[10];
    String CPN_SCSSCKDT = "SCSSCKDT";
    BPOT8014_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPOT8014_WS_TEMP_VARIABLE();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCWOUT SCCWOUT = new SCCWOUT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    BPCSQTYP BPCSQTYP = new BPCSQTYP();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCGWA SCCGWA;
    BPB8014_AWA_8014 BPB8014_AWA_8014;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        B000_MAIN_PROCESS();
        CEP.TRC(SCCGWA, "BPOT8014 return!");
        Z_RET();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8014_AWA_8014>");
        BPB8014_AWA_8014 = (BPB8014_AWA_8014) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, WS_TEMP_VARIABLE);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B100_READ_PROCESS();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPB8014_AWA_8014.DATE1 == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_DATE_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8014_AWA_8014.DATE1_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB8014_AWA_8014.DATE1;
            SCSSCKDT SCSSCKDT1 = new SCSSCKDT();
            SCSSCKDT1.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR, WS_TEMP_VARIABLE.WS_MSGID);
                WS_TEMP_VARIABLE.WS_FLD_NO = BPB8014_AWA_8014.DATE1_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB8014_AWA_8014.DATE2 == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8014_AWA_8014.DATE2_NO;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCCKDT);
            SCCCKDT.DATE = BPB8014_AWA_8014.DATE2;
            SCSSCKDT SCSSCKDT2 = new SCSSCKDT();
            SCSSCKDT2.MP(SCCGWA, SCCCKDT);
            if (SCCCKDT.RC != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_DATE_ERR, WS_TEMP_VARIABLE.WS_MSGID);
                WS_TEMP_VARIABLE.WS_FLD_NO = BPB8014_AWA_8014.DATE1_NO;
                S000_ERR_MSG_PROC();
            }
        }
        if (BPB8014_AWA_8014.DATE1 > SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_GT_ACDATE, WS_TEMP_VARIABLE.WS_MSGID);
            S000_ERR_MSG_PROC();
        }
        if (BPB8014_AWA_8014.DATE1 > BPB8014_AWA_8014.DATE2) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_S_DATE_GT_E_DATE, WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8014_AWA_8014.DATE1_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void B100_READ_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSQTYP);
        BPCSQTYP.DATE1 = BPB8014_AWA_8014.DATE1;
        BPCSQTYP.DATE2 = BPB8014_AWA_8014.DATE2;
        BPCSQTYP.CITY_CD = BPB8014_AWA_8014.CITY_CD;
        S000_CALL_BPZSQTYP();
        if (BPCSQTYP.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSQTYP.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_MSGID);
            WS_TEMP_VARIABLE.WS_FLD_NO = BPB8014_AWA_8014.DATE1_NO;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZSQTYP() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-S-INQ-TYPH-STS   ", BPCSQTYP);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void S000_ERR_MSG_PROC_CONTINUE() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_TEMP_VARIABLE.WS_MSGID);
        CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_FLD_NO);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
