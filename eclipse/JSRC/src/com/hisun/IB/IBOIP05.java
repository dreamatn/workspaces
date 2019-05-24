package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.SM.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBOIP05 {
    String JIBS_tmp_str[] = new String[10];
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    String PGM_SCSSCLDT = "SCSSCLDT";
    IBOIP05_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new IBOIP05_WS_TEMP_VARIABLE();
    IBOIP05_WS_ERR_MSG WS_ERR_MSG = new IBOIP05_WS_ERR_MSG();
    SMRCTLT SMRCTLT = new SMRCTLT();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPPM BPRPPM = new BPRPPM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBRMST IBRMST = new IBRMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    SCCGCPT SCCGCPT;
    SCCGMSG SCCGMSG;
    BPCPQBNK_DATA_INFO BPCRBANK;
    BPRTRT BPRTRTT;
    IBB0005_AWA_0005 IBB0005_AWA_0005;
    SCCAWAC SCCAWAC;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        B00_CHECK_PROC();
        CEP.TRC(SCCGWA, "IBOIP05 return!");
        Z_RET();
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGCPT = (SCCGCPT) SCCGSCA_SC_AREA.CMPT_AREA_PTR;
        SCCGMSG = (SCCGMSG) SCCGSCA_SC_AREA.MSG_AREA_PTR;
        BPRTRTT = (BPRTRT) SCCGSCA_SC_AREA.TR_PARM_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "IBB0005_AWA_0005>");
        IBB0005_AWA_0005 = (IBB0005_AWA_0005) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        SCCAWAC = new SCCAWAC();
        IBS.init(SCCGWA, SCCAWAC);
        IBS.CPY2CLS(SCCGWA, SCCGWA.COMM_AREA.AWAC_AREA_PTR, SCCAWAC);
        WS_TEMP_VARIABLE.WS_T_CTL = BPRTRTT.DATA_TXT.PGM[SCCGWA.COMM_AREA.PGM_NO-1].PGM_CTL;
        IBS.CPY2CLS(SCCGWA, WS_TEMP_VARIABLE.WS_T_CTL, WS_TEMP_VARIABLE.REDEFINES7);
        IBS.init(SCCGWA, SCCMSG);
    }
    public void B00_CHECK_PROC() throws IOException,SQLException,Exception {
        WS_ERR_MSG.WS_AP_MMO = "IB";
        CEP.TRC(SCCGWA, IBB0005_AWA_0005.FROMDATE);
        if (IBB0005_AWA_0005.FROMDATE_NO != 0) {
            B01_CHECK_FROMDATE();
        }
        S00_CHECK_RESULT_PROC();
    }
    public void B01_CHECK_FROMDATE() throws IOException,SQLException,Exception {
        WS_TEMP_VARIABLE.WS_TRN_TYPE = WS_TEMP_VARIABLE.REDEFINES7.WS_T_C1.charAt(0);
        CEP.TRC(SCCGWA, IBB0005_AWA_0005.FROMDATE);
        if (WS_TEMP_VARIABLE.WS_TRN_TYPE == 'N') {
            CEP.TRC(SCCGWA, IBB0005_AWA_0005.FROMDATE);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            if (IBB0005_AWA_0005.FROMDATE < SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.FROMDATE_INVALID, WS_ERR_MSG);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
                CEP.ERRC(SCCGWA, JIBS_tmp_str[0], IBB0005_AWA_0005.FROMDATE_NO);
            }
        } else if (WS_TEMP_VARIABLE.WS_TRN_TYPE == 'M') {
            CEP.TRC(SCCGWA, IBB0005_AWA_0005.FROMDATE);
            if ((IBB0005_AWA_0005.FROMDATE == 0 
                && IBB0005_AWA_0005.TODATE == 0 
                && IBB0005_AWA_0005.FINPDATE == 0 
                && IBB0005_AWA_0005.TINPDATE == 0)) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT, WS_ERR_MSG);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
                CEP.ERRC(SCCGWA, JIBS_tmp_str[0], IBB0005_AWA_0005.FINPDATE_NO);
            }
            if (IBB0005_AWA_0005.FROMDATE > IBB0005_AWA_0005.TODATE) {
                CEP.TRC(SCCGWA, "XXXXX");
                CEP.TRC(SCCGWA, IBB0005_AWA_0005.FROMDATE);
                CEP.TRC(SCCGWA, IBB0005_AWA_0005.TODATE);
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.FROMDATE_INVALID, WS_ERR_MSG);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
                CEP.ERRC(SCCGWA, JIBS_tmp_str[0], IBB0005_AWA_0005.FROMDATE_NO);
            }
            if (IBB0005_AWA_0005.FINPDATE > IBB0005_AWA_0005.TINPDATE) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.FROMDATE_INVALID, WS_ERR_MSG);
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
                CEP.ERRC(SCCGWA, JIBS_tmp_str[0], IBB0005_AWA_0005.FINPDATE_NO);
            }
        } else {
        }
    }
    public void B02_CHECK_TODATE() throws IOException,SQLException,Exception {
        WS_TEMP_VARIABLE.WS_TRN_TYPE = WS_TEMP_VARIABLE.REDEFINES7.WS_T_C1.charAt(0);
        WS_TEMP_VARIABLE.WS_CURS_POS = IBB0005_AWA_0005.TODATE_NO;
        if (IBB0005_AWA_0005.TODATE < IBB0005_AWA_0005.FROMDATE) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.TODATE_INVALID, WS_ERR_MSG);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0], WS_TEMP_VARIABLE.WS_CURS_POS);
        }
    }
    public void S00_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            WS_ERR_MSG.WS_AP_MMO = "SC";
            WS_ERR_MSG.REDEFINES13.WS_ERR_MSGID = SCCCLDT.RC;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG.REDEFINES13);
            WS_ERR_MSG.WS_ERR_MSGID_X = Short.parseShort(JIBS_tmp_str[0]);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, WS_ERR_MSG);
            CEP.ERRC(SCCGWA, JIBS_tmp_str[0]);
        }
        WS_ERR_MSG.WS_AP_MMO = "IB";
    }
    public void S00_CHECK_RESULT_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
