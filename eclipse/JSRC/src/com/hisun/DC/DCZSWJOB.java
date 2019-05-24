package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.EA.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSWJOB {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm DCTDCICT_RD;
    DBParm DCTCDDAT_RD;
    brParm DCTDCICT_BR = new brParm();
    boolean pgmRtn = false;
    String K_PGM_NAME = "DCZSWJOB";
    String CPN_PARM_MT = "DC-UPDATE-CARD-BLS";
    String K_HIS_REMARK = "UPDATE CARD BLS";
    String K_HIS_COPYBOOK = "DCCSWJOB";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String K_OUTPUT_FMT = "DC335";
    String WS_ERR_MSG = " ";
    DCZSWJOB_WS_TEMP WS_TEMP = new DCZSWJOB_WS_TEMP();
    DCZSWJOB_WS_DATA_TO_MAC WS_DATA_TO_MAC = new DCZSWJOB_WS_DATA_TO_MAC();
    DCZSWJOB_WS_DATA_TO_MAC2 WS_DATA_TO_MAC2 = new DCZSWJOB_WS_DATA_TO_MAC2();
    DCZSWJOB_WS_JOB1 WS_JOB1 = new DCZSWJOB_WS_JOB1();
    DCZSWJOB_WS_JOB2 WS_JOB2 = new DCZSWJOB_WS_JOB2();
    String WS_OUTPUT1 = " ";
    String WS_OUTPUT2 = " ";
    double WS_CARD_BALANCE = 0;
    String WS_COUNT_CNT = " ";
    DCZSWJOB_WS_OUTPUT WS_OUTPUT = new DCZSWJOB_WS_OUTPUT();
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCRAND SCCRAND = new SCCRAND();
    SCCCALL SCCCALL = new SCCCALL();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCSTAR SCCSTAR = new SCCSTAR();
    SCCTSSC SCCTSSC = new SCCTSSC();
    DCCF335 DCCF335 = new DCCF335();
    DCRDCICT DCRDCICT = new DCRDCICT();
    CICQCIAC CICQCIAC = new CICQCIAC();
    CICQACRL CICQACRL = new CICQACRL();
    EACSBINQ EACSBINQ = new EACSBINQ();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    int WS_TXN_DT = 0;
    String WS_CARD_NO = " ";
    SCCGWA SCCGWA;
    DCCSWJOB DCCSWJOB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DCCSWJOB DCCSWJOB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSWJOB = DCCSWJOB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSWJOB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void A010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DCCSWJOB.CARD_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO);
        }
        if (DCCSWJOB.CARD_SEQ == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_SEQ);
        }
        if (DCCSWJOB.CARD_ARQC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_ARQC);
        }
        if (DCCSWJOB.CARD_ARQC_DATA.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_ARQC_DATA);
        }
        if (DCCSWJOB.ISSUE_DATA.trim().length() == 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_ISSU_DATA);
        }
        if (DCCSWJOB.FUNC != 'C') {
            if (DCCSWJOB.UPDATE_AMT == ' ') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_UPD_AMT);
            }
        }
        if (DCCSWJOB.CARD_BALANCE == ' ') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_MUST_INPUT_BLS);
        }
        CEP.TRC(SCCGWA, DCCSWJOB.TXN_TYPE);
        if (DCCSWJOB.TXN_TYPE == 'T') {
            IBS.init(SCCGWA, DCRDCICT);
            DCRDCICT.CARD_NO = DCCSWJOB.CARD_NO;
            DCRDCICT.TXN_TYP = '4';
            DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_DCTDCICT();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ONLY_ALLOW_ONE_TIME);
            }
        }
        if (DCCSWJOB.TXN_TYPE == 'C') {
            CEP.TRC(SCCGWA, DCCSWJOB.UPDATE_AMT);
            CEP.TRC(SCCGWA, DCCSWJOB.CARD_BALANCE);
            if (DCCSWJOB.UPDATE_AMT > DCCSWJOB.CARD_BALANCE) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_GT_CARD_BALANCE);
            }
            IBS.init(SCCGWA, DCRDCICT);
            DCRDCICT.CARD_NO = DCCSWJOB.CARD_NO;
            DCRDCICT.TXN_TYP = '7';
            DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_DCTDCICT();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ALR_QUANCUN_CANCEL);
            }
            IBS.init(SCCGWA, DCRDCICT);
            DCRDCICT.KEY.TXN_JANNO = DCCSWJOB.JRN_NO;
            DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_DCTDCICT1();
            if (pgmRtn) return;
            WS_COUNT_CNT = DCRDCICT.COUNT_CNT;
            CEP.TRC(SCCGWA, WS_COUNT_CNT);
            IBS.init(SCCGWA, DCRDCICT);
            WS_TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
            WS_CARD_NO = DCCSWJOB.CARD_NO;
            T000_STARTBR_DCTDCICT();
            if (pgmRtn) return;
            T000_READNEXT_DCTDCICT();
            if (pgmRtn) return;
            while (WS_TBL_FLAG != 'N') {
                CEP.TRC(SCCGWA, DCRDCICT.COUNT_CNT);
                if (DCRDCICT.COUNT_CNT.compareTo(WS_COUNT_CNT) > 0) {
                    WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_QUANCUN_TRD;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
                T000_READNEXT_DCTDCICT();
                if (pgmRtn) return;
            }
            T000_ENDBR_DCTDCICT();
            if (pgmRtn) return;
        }
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        if (DCCSWJOB.FUNC == 'C') {
            B100_CLOCK_CARD();
            if (pgmRtn) return;
            C000_OUTPUT1_PROCESS();
            if (pgmRtn) return;
        } else if (DCCSWJOB.FUNC == 'X') {
            B050_CHECK_AC_LINK();
            if (pgmRtn) return;
            B060_CHECK_EALINK();
            if (pgmRtn) return;
            B100_CLOCK_CARD();
            if (pgmRtn) return;
            C000_OUTPUT1_PROCESS();
            if (pgmRtn) return;
        } else if (DCCSWJOB.FUNC == 'A') {
            B015_CHECK_CARD_BALANCE();
            if (pgmRtn) return;
            B200_ADD_BALANCE();
            if (pgmRtn) return;
            C000_OUTPUT2_PROCESS();
            if (pgmRtn) return;
        } else if (DCCSWJOB.FUNC == 'S') {
            B015_CHECK_CARD_BALANCE();
            if (pgmRtn) return;
            B300_SUBS_BALANCE();
            if (pgmRtn) return;
            C000_OUTPUT3_PROCESS();
            if (pgmRtn) return;
            B400_WRITE_DCTDCICT();
            if (pgmRtn) return;
        } else if (DCCSWJOB.FUNC == 'R') {
            B015_CHECK_CARD_BALANCE();
            if (pgmRtn) return;
            B300_SUBS_BALANCE();
            if (pgmRtn) return;
            C000_OUTPUT2_PROCESS();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_FUNC_ERR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B050_CHECK_AC_LINK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQCIAC);
        CICQCIAC.FUNC = '3';
        CICQCIAC.DATA.AGR_NO = DCCSWJOB.CARD_NO;
        CICQCIAC.DATA.FRM_APP = "TD";
        S000_CALL_CIZQCIAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO);
        if (CICQCIAC.DATA.ACR_AREA.ENTY_INF[1-1].ENTY_NO.trim().length() > 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_EST_TD_BAL);
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.REL_AC_NO = DCCSWJOB.CARD_NO;
        CICQACRL.DATA.AC_REL = "05";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        if (CICQACRL.O_DATA.O_REL_STS == '0') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_LINK_OTH_AC);
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.REL_AC_NO = DCCSWJOB.CARD_NO;
        CICQACRL.DATA.AC_REL = "06";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        if (CICQACRL.O_DATA.O_REL_STS == '0') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_LINK_OTH_AC);
        }
        IBS.init(SCCGWA, CICQACRL);
        CICQACRL.FUNC = 'I';
        CICQACRL.DATA.REL_AC_NO = DCCSWJOB.CARD_NO;
        CICQACRL.DATA.AC_REL = "07";
        S000_CALL_CIZQACRL();
        if (pgmRtn) return;
        if (CICQACRL.O_DATA.O_REL_STS == '0') {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_LINK_OTH_AC);
        }
    }
    public void B060_CHECK_EALINK() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSWJOB.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (DCRCDDAT.ACNO_TYPE == '1') {
            IBS.init(SCCGWA, EACSBINQ);
            EACSBINQ.I_AC = DCCSWJOB.CARD_NO;
            S000_CALL_EAZSBINQ();
            if (pgmRtn) return;
            if (EACSBINQ.END_SEQ > 0) {
                CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_EALINK_EXIST);
            }
        }
    }
    public void B100_CLOCK_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = '4';
        if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
        JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
        if (DCCSWJOB.CARD_NO.substring(0, 6).equalsIgnoreCase("621241")) {
            if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
            JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSWJOB.CARD_NO.substring(0, 9);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        } else {
            if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
            JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSWJOB.CARD_NO.substring(0, 6);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_NODEID = "��Կ��������";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.ISSUE_DATA == null) DCCSWJOB.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSWJOB.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSWJOB.ISSUE_DATA += " ";
        if (DCCSWJOB.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("01")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "MDK-Mac-Seed";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        if (DCCSWJOB.ISSUE_DATA == null) DCCSWJOB.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSWJOB.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSWJOB.ISSUE_DATA += " ";
        if (DCCSWJOB.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("04")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "RMDKMAC";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_KEYINDEX = "1";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
        JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
        WS_TEMP.WS_TEMP_CARD_14 = DCCSWJOB.CARD_NO.substring(6 - 1, 6 + 14 - 1);
        WS_TEMP.WS_TEMP_CARD_SEQ = DCCSWJOB.CARD_SEQ;
        SCCTSSC.COMM_AREA_4.4_UDKGENE = IBS.CLS2CPY(SCCGWA, WS_TEMP);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.CARD_ARQC_DATA == null) DCCSWJOB.CARD_ARQC_DATA = "";
        JIBS_tmp_int = DCCSWJOB.CARD_ARQC_DATA.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) DCCSWJOB.CARD_ARQC_DATA += " ";
        SCCTSSC.COMM_AREA_4.4_SKGENE = DCCSWJOB.CARD_ARQC_DATA.substring(63 - 1, 63 + 4 - 1);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        SCCTSSC.COMM_AREA_4.4_IV = "0000000000000000";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        WS_DATA_TO_MAC.WS_DATA1 = "841E000004";
        WS_DATA_TO_MAC.WS_DATA2 = SCCTSSC.COMM_AREA_4.4_SKGENE;
        WS_DATA_TO_MAC.WS_DATA3 = DCCSWJOB.CARD_ARQC;
        SCCTSSC.COMM_AREA_4.4_DATATO_MAC = IBS.CLS2CPY(SCCGWA, WS_DATA_TO_MAC);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        S000_CALL_SCZTSSC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_4.4_O_MAC);
        WS_JOB1.WS_TAG1 = "72";
        WS_JOB1.WS_LEN1 = "13";
        WS_JOB1.WS_TAG2 = "9F18";
        WS_JOB1.WS_LEN2 = "04";
        WS_JOB1.WS_ISSU_DATA = "00000001";
        WS_JOB1.WS_TAG3 = "86";
        WS_JOB1.WS_LEN3 = "0A";
        WS_JOB1.WS_CLA = "84";
        WS_JOB1.WS_INS = "1E";
        WS_JOB1.WS_P1 = "00";
        WS_JOB1.WS_P2 = "00";
        WS_JOB1.WS_LC = "04";
        if (SCCTSSC.COMM_AREA_4.4_O_MAC == null) SCCTSSC.COMM_AREA_4.4_O_MAC = "";
        JIBS_tmp_int = SCCTSSC.COMM_AREA_4.4_O_MAC.length();
        for (int i=0;i<2048-JIBS_tmp_int;i++) SCCTSSC.COMM_AREA_4.4_O_MAC += " ";
        WS_JOB1.WS_MAC = SCCTSSC.COMM_AREA_4.4_O_MAC.substring(0, 8);
        WS_JOB1.WS_LE = "00";
        CEP.TRC(SCCGWA, WS_JOB1);
    }
    public void B015_CHECK_CARD_BALANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = '4';
        if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
        JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
        if (DCCSWJOB.CARD_NO.substring(0, 6).equalsIgnoreCase("621241")) {
            if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
            JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSWJOB.CARD_NO.substring(0, 9);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        } else {
            if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
            JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSWJOB.CARD_NO.substring(0, 6);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_NODEID = "��Կ��������";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.ISSUE_DATA == null) DCCSWJOB.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSWJOB.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSWJOB.ISSUE_DATA += " ";
        if (DCCSWJOB.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("01")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "MDK-Mac-Seed";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        if (DCCSWJOB.ISSUE_DATA == null) DCCSWJOB.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSWJOB.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSWJOB.ISSUE_DATA += " ";
        if (DCCSWJOB.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("04")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "RMDKMAC";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_KEYINDEX = "1";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
        JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
        WS_TEMP.WS_TEMP_CARD_14 = DCCSWJOB.CARD_NO.substring(6 - 1, 6 + 14 - 1);
        WS_TEMP.WS_TEMP_CARD_SEQ = DCCSWJOB.CARD_SEQ;
        SCCTSSC.COMM_AREA_4.4_UDKGENE = IBS.CLS2CPY(SCCGWA, WS_TEMP);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.CARD_ARQC_DATA == null) DCCSWJOB.CARD_ARQC_DATA = "";
        JIBS_tmp_int = DCCSWJOB.CARD_ARQC_DATA.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) DCCSWJOB.CARD_ARQC_DATA += " ";
        SCCTSSC.COMM_AREA_4.4_SKGENE = DCCSWJOB.CARD_ARQC_DATA.substring(63 - 1, 63 + 4 - 1);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        SCCTSSC.COMM_AREA_4.4_IV = "0000000000000000";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        WS_DATA_TO_MAC2.WS_DATA21 = SCCTSSC.COMM_AREA_4.4_SKGENE;
        JIBS_tmp_str[0] = "" + DCCSWJOB.CARD_BALANCE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_DATA_TO_MAC2.WS_DATA22 = JIBS_tmp_str[0].substring(7 - 1, 7 + 10 - 1);
        WS_DATA_TO_MAC2.WS_DATA23 = "00";
        SCCTSSC.COMM_AREA_4.4_DATATO_MAC = IBS.CLS2CPY(SCCGWA, WS_DATA_TO_MAC2);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        S000_CALL_SCZTSSC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_4.4_O_MAC);
        CEP.TRC(SCCGWA, DCCSWJOB.ISSUE_DATA);
        if (DCCSWJOB.ISSUE_DATA == null) DCCSWJOB.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSWJOB.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSWJOB.ISSUE_DATA += " ";
        if (SCCTSSC.COMM_AREA_4.4_O_MAC == null) SCCTSSC.COMM_AREA_4.4_O_MAC = "";
        JIBS_tmp_int = SCCTSSC.COMM_AREA_4.4_O_MAC.length();
        for (int i=0;i<2048-JIBS_tmp_int;i++) SCCTSSC.COMM_AREA_4.4_O_MAC += " ";
        if (!DCCSWJOB.ISSUE_DATA.substring(31 - 1, 31 + 8 - 1).equalsIgnoreCase(SCCTSSC.COMM_AREA_4.4_O_MAC.substring(0, 8))) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_CARD_BALANCE_WRONG);
        }
    }
    public void B200_ADD_BALANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = '4';
        if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
        JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
        if (DCCSWJOB.CARD_NO.substring(0, 6).equalsIgnoreCase("621241")) {
            if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
            JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSWJOB.CARD_NO.substring(0, 9);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        } else {
            if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
            JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSWJOB.CARD_NO.substring(0, 6);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_NODEID = "��Կ��������";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.ISSUE_DATA == null) DCCSWJOB.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSWJOB.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSWJOB.ISSUE_DATA += " ";
        if (DCCSWJOB.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("01")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "MDK-Mac-Seed";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        if (DCCSWJOB.ISSUE_DATA == null) DCCSWJOB.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSWJOB.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSWJOB.ISSUE_DATA += " ";
        if (DCCSWJOB.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("04")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "RMDKMAC";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_KEYINDEX = "1";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
        JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
        WS_TEMP.WS_TEMP_CARD_14 = DCCSWJOB.CARD_NO.substring(6 - 1, 6 + 14 - 1);
        WS_TEMP.WS_TEMP_CARD_SEQ = DCCSWJOB.CARD_SEQ;
        SCCTSSC.COMM_AREA_4.4_UDKGENE = IBS.CLS2CPY(SCCGWA, WS_TEMP);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.CARD_ARQC_DATA == null) DCCSWJOB.CARD_ARQC_DATA = "";
        JIBS_tmp_int = DCCSWJOB.CARD_ARQC_DATA.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) DCCSWJOB.CARD_ARQC_DATA += " ";
        SCCTSSC.COMM_AREA_4.4_SKGENE = DCCSWJOB.CARD_ARQC_DATA.substring(63 - 1, 63 + 4 - 1);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        SCCTSSC.COMM_AREA_4.4_IV = "0000000000000000";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        WS_DATA_TO_MAC.WS_DATA1 = "04DA9F790A";
        WS_DATA_TO_MAC.WS_DATA2 = SCCTSSC.COMM_AREA_4.4_SKGENE;
        WS_DATA_TO_MAC.WS_DATA3 = DCCSWJOB.CARD_ARQC;
        WS_CARD_BALANCE = DCCSWJOB.CARD_BALANCE + DCCSWJOB.UPDATE_AMT;
        CEP.TRC(SCCGWA, WS_CARD_BALANCE);
        JIBS_tmp_str[0] = "" + WS_CARD_BALANCE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_DATA_TO_MAC.WS_DATA4 = JIBS_tmp_str[0].substring(5 - 1, 5 + 12 - 1);
        SCCTSSC.COMM_AREA_4.4_DATATO_MAC = IBS.CLS2CPY(SCCGWA, WS_DATA_TO_MAC);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        S000_CALL_SCZTSSC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_4.4_O_MAC);
        WS_JOB2.WS_TAG21 = "72";
        WS_JOB2.WS_LEN21 = "19";
        WS_JOB2.WS_TAG22 = "9F18";
        WS_JOB2.WS_LEN22 = "04";
        WS_JOB2.WS_ISSU_DATA2 = "00000001";
        WS_JOB2.WS_TAG23 = "86";
        WS_JOB2.WS_LEN23 = "10";
        WS_JOB2.WS_TAG24 = "04DA9F790A";
        JIBS_tmp_str[0] = "" + WS_CARD_BALANCE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_JOB2.WS_AMT = JIBS_tmp_str[0].substring(5 - 1, 5 + 12 - 1);
        if (SCCTSSC.COMM_AREA_4.4_O_MAC == null) SCCTSSC.COMM_AREA_4.4_O_MAC = "";
        JIBS_tmp_int = SCCTSSC.COMM_AREA_4.4_O_MAC.length();
        for (int i=0;i<2048-JIBS_tmp_int;i++) SCCTSSC.COMM_AREA_4.4_O_MAC += " ";
        WS_JOB2.WS_MAC2 = SCCTSSC.COMM_AREA_4.4_O_MAC.substring(0, 8);
        WS_JOB2.WS_LE2 = "00";
        CEP.TRC(SCCGWA, WS_JOB2);
    }
    public void B300_SUBS_BALANCE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCTSSC);
        SCCTSSC.FUNC = '4';
        if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
        JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
        if (DCCSWJOB.CARD_NO.substring(0, 6).equalsIgnoreCase("621241")) {
            if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
            JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSWJOB.CARD_NO.substring(0, 9);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        } else {
            if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
            JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
            for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
            SCCTSSC.COMM_AREA_4.4_DESIGNID = DCCSWJOB.CARD_NO.substring(0, 6);
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_NODEID = "��Կ��������";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.ISSUE_DATA == null) DCCSWJOB.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSWJOB.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSWJOB.ISSUE_DATA += " ";
        if (DCCSWJOB.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("01")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "MDK-Mac-Seed";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        if (DCCSWJOB.ISSUE_DATA == null) DCCSWJOB.ISSUE_DATA = "";
        JIBS_tmp_int = DCCSWJOB.ISSUE_DATA.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCCSWJOB.ISSUE_DATA += " ";
        if (DCCSWJOB.ISSUE_DATA.substring(15 - 1, 15 + 2 - 1).equalsIgnoreCase("04")) {
            SCCTSSC.COMM_AREA_4.4_KEYMODEL_ID = "RMDKMAC";
            SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        }
        SCCTSSC.COMM_AREA_4.4_KEYINDEX = "1";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.CARD_NO == null) DCCSWJOB.CARD_NO = "";
        JIBS_tmp_int = DCCSWJOB.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSWJOB.CARD_NO += " ";
        WS_TEMP.WS_TEMP_CARD_14 = DCCSWJOB.CARD_NO.substring(6 - 1, 6 + 14 - 1);
        WS_TEMP.WS_TEMP_CARD_SEQ = DCCSWJOB.CARD_SEQ;
        SCCTSSC.COMM_AREA_4.4_UDKGENE = IBS.CLS2CPY(SCCGWA, WS_TEMP);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        if (DCCSWJOB.CARD_ARQC_DATA == null) DCCSWJOB.CARD_ARQC_DATA = "";
        JIBS_tmp_int = DCCSWJOB.CARD_ARQC_DATA.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) DCCSWJOB.CARD_ARQC_DATA += " ";
        SCCTSSC.COMM_AREA_4.4_SKGENE = DCCSWJOB.CARD_ARQC_DATA.substring(63 - 1, 63 + 4 - 1);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        SCCTSSC.COMM_AREA_4.4_IV = "0000000000000000";
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        WS_DATA_TO_MAC.WS_DATA1 = "04DA9F790A";
        WS_DATA_TO_MAC.WS_DATA2 = SCCTSSC.COMM_AREA_4.4_SKGENE;
        WS_DATA_TO_MAC.WS_DATA3 = DCCSWJOB.CARD_ARQC;
        WS_CARD_BALANCE = DCCSWJOB.CARD_BALANCE - DCCSWJOB.UPDATE_AMT;
        CEP.TRC(SCCGWA, WS_CARD_BALANCE);
        JIBS_tmp_str[0] = "" + WS_CARD_BALANCE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_DATA_TO_MAC.WS_DATA4 = JIBS_tmp_str[0].substring(5 - 1, 5 + 12 - 1);
        SCCTSSC.COMM_AREA_4.4_DATATO_MAC = IBS.CLS2CPY(SCCGWA, WS_DATA_TO_MAC);
        SCCTSSC.COMM_AREA = IBS.CLS2CPY(SCCGWA, SCCTSSC.COMM_AREA_4);
        S000_CALL_SCZTSSC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCTSSC.COMM_AREA_4.4_O_MAC);
        WS_JOB2.WS_TAG21 = "72";
        WS_JOB2.WS_LEN21 = "19";
        WS_JOB2.WS_TAG22 = "9F18";
        WS_JOB2.WS_LEN22 = "04";
        WS_JOB2.WS_ISSU_DATA2 = "00000001";
        WS_JOB2.WS_TAG23 = "86";
        WS_JOB2.WS_LEN23 = "10";
        WS_JOB2.WS_TAG24 = "04DA9F790A";
        JIBS_tmp_str[0] = "" + WS_CARD_BALANCE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<16-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        WS_JOB2.WS_AMT = JIBS_tmp_str[0].substring(5 - 1, 5 + 12 - 1);
        if (SCCTSSC.COMM_AREA_4.4_O_MAC == null) SCCTSSC.COMM_AREA_4.4_O_MAC = "";
        JIBS_tmp_int = SCCTSSC.COMM_AREA_4.4_O_MAC.length();
        for (int i=0;i<2048-JIBS_tmp_int;i++) SCCTSSC.COMM_AREA_4.4_O_MAC += " ";
        WS_JOB2.WS_MAC2 = SCCTSSC.COMM_AREA_4.4_O_MAC.substring(0, 8);
        WS_JOB2.WS_LE2 = "00";
        CEP.TRC(SCCGWA, WS_JOB2);
    }
    public void B400_WRITE_DCTDCICT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.KEY.TXN_JANNO = SCCGWA.COMM_AREA.JRN_NO;
        DCRDCICT.CARD_NO = DCCSWJOB.CARD_NO;
        DCRDCICT.TXN_TYP = '4';
        DCRDCICT.TXN_AMT = DCCSWJOB.CARD_BALANCE;
        DCRDCICT.TXN_CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        DCRDCICT.TXN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRDCICT.TXN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDCICT.WRITE_CARD_STS = '1';
        DCRDCICT.TXN_STS = '1';
        CEP.TRC(SCCGWA, DCCSWJOB.COUNT_NO);
        DCRDCICT.COUNT_CNT = DCCSWJOB.COUNT_NO;
        DCRDCICT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDCICT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDCICT();
        if (pgmRtn) return;
    }
    public void C000_OUTPUT1_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF335);
        DCCF335.WJOB = IBS.CLS2CPY(SCCGWA, WS_JOB1);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF335;
        SCCFMT.DATA_LEN = 2800;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void C000_OUTPUT2_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF335);
        DCCF335.WJOB = IBS.CLS2CPY(SCCGWA, WS_JOB2);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF335;
        SCCFMT.DATA_LEN = 2800;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void C000_OUTPUT3_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_O_WJOB = IBS.CLS2CPY(SCCGWA, WS_JOB2);
        WS_OUTPUT.WS_O_TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_O_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 2820;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        DCTDCICT_RD.where = "CARD_NO = :DCRDCICT.CARD_NO "
            + "AND TXN_DT = :DCRDCICT.KEY.TXN_DT "
            + "AND TXN_TYP = :DCRDCICT.TXN_TYP";
        IBS.READ(SCCGWA, DCRDCICT, this, DCTDCICT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCICT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTDCICT1() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        IBS.READ(SCCGWA, DCRDCICT, DCTDCICT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DCICT_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCICT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        DCTDCICT_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DCRDCICT, DCTDCICT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_BR.rp = new DBParm();
        DCTDCICT_BR.rp.TableName = "DCTDCICT";
        DCTDCICT_BR.rp.where = "TXN_DT = :WS_TXN_DT "
            + "AND CARD_NO = :WS_CARD_NO "
            + "AND ( TXN_TYP = '0' "
            + "OR TXN_TYP = '1' "
            + "OR TXN_TYP = '2' )";
        IBS.STARTBR(SCCGWA, DCRDCICT, this, DCTDCICT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCICT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DCTDCICT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRDCICT, this, DCTDCICT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCICT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DCTDCICT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DCTDCICT_BR);
    }
    public void S000_CALL_SCZTSSC() throws IOException,SQLException,Exception {
        SCZTSSC SCZTSSC = new SCZTSSC();
        SCZTSSC.MP(SCCGWA, SCCTSSC);
        if (SCCTSSC.RC != 0) {
            CEP.ERR(SCCGWA, DCCMSG_ERROR_MSG.DC_ERROR_FROM_ENP);
        }
    }
    public void S000_CALL_CIZQCIAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST-ACR", CICQCIAC);
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
    }
    public void S000_CALL_EAZSBINQ() throws IOException,SQLException,Exception {
        EAZSBINQ EAZSBINQ = new EAZSBINQ();
        EAZSBINQ.MP(SCCGWA, EACSBINQ);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
