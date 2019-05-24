package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSGTCD {
    int JIBS_tmp_int;
    DBParm DCTBINPM_RD;
    DBParm DCTCDDAT_RD;
    DBParm DCTCDORD_RD;
    DBParm DCTINRCD_RD;
    DBParm DCTCTCDC_RD;
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DC063";
    String WS_ERR_MSG = " ";
    String WS_SOCIAL_CARD_NO = " ";
    short WS_SOCIAL_CARD_SEQ = 0;
    String WS_FINANCIAL_CARD_NO = " ";
    short WS_FINANCIAL_CARD_SEQ = 0;
    String WS_VOUCHER_CARD_NO = " ";
    String WS_CI_NO = " ";
    String WS_ID_TYPE = " ";
    String WS_ID_NO = " ";
    String WS_CI_NM = " ";
    char WS_ISSUE_MTH0 = ' ';
    char WS_ENV_FLG0 = ' ';
    short WS_CARD_SEQ1 = 0;
    String WS_SOCIAL_PSW = " ";
    String WS_CARD_PSW = " ";
    String WS_OLD_CARD_NO = " ";
    String WS_TXN_PSW = " ";
    DCZSGTCD_WS_OUTPUT WS_OUTPUT = new DCZSGTCD_WS_OUTPUT();
    short WS_CHANGE_TIM = 0;
    char WS_CARD_FLG = ' ';
    char WS_TBL_FLAG = ' ';
    char WS_I_ENV_FLG = ' ';
    char WS_SOCIAL_EVN_FLG = 'N';
    char WS_CARD_TYPE = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRCDORD DCRCDORD = new DCRCDORD();
    DCRBINPM DCRBINPM = new DCRBINPM();
    SCCCLDT SCCCLDT = new SCCCLDT();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    CICCUST CICCUST = new CICCUST();
    DCCUCSET DCCUCSET = new DCCUCSET();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DCCSGTCD DCCHISTY = new DCCSGTCD();
    DCRINRCD DCRINRCD = new DCRINRCD();
    DCRCTCDC DCRCTCDC = new DCRCTCDC();
    DCRPRDPR DCRPRDPR = new DCRPRDPR();
    DCCUPRCD DCCUPRCD = new DCCUPRCD();
    CICQCHDC CICQCHDC = new CICQCHDC();
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    DCCS9991 DCCS9991 = new DCCS9991();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    DCCSGTCD DCCSGTCD;
    public void MP(SCCGWA SCCGWA, DCCSGTCD DCCSGTCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSGTCD = DCCSGTCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSGTCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        WS_CARD_FLG = ' ';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_INQ_ORDER_FILE();
        if (pgmRtn) return;
        B025_CARD_VOUCHER();
        if (pgmRtn) return;
        B030_UPDATE_ORDER_FILE();
        if (pgmRtn) return;
        B040_CARD_PROCESS();
        if (pgmRtn) return;
        B050_OUTPUT_PROCESS();
        if (pgmRtn) return;
        B060_HISTORY_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSGTCD.CARD_NO);
        CEP.TRC(SCCGWA, DCCSGTCD.CARD_SEQ);
        if (DCCSGTCD.CARD_NO.trim().length() == 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_NO;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCCSGTCD.CARD_SEQ == ' ') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_MUST_INPUT_CARD_SEQ;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSGTCD.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            CEP.TRC(SCCGWA, DCRCDDAT.CARD_STS);
            if (DCRCDDAT.CARD_STS != '3') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_STS_MUST_MADE;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            WS_CHANGE_TIM = DCRCDDAT.EXC_CARD_TMS;
        }
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        CEP.TRC(SCCGWA, DCCSGTCD.CITIZEN_CRD_FLG);
        if (DCCSGTCD.CITIZEN_CRD_FLG == 'Y') {
            WS_CARD_TYPE = 'Y';
            B015_CHECK_CITIZEN_CRD();
            if (pgmRtn) return;
        } else {
            WS_CARD_TYPE = 'N';
        }
        if (DCCSGTCD.CARD_STS == '0') {
            IBS.init(SCCGWA, DCRCDDAT);
            DCRCDDAT.KEY.CARD_NO = DCCSGTCD.CARD_NO;
            T000_READ_DCTCDDAT();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'Y') {
                if (DCRCDDAT.TRAN_PIN_DAT.trim().length() > 0) {
                    WS_TXN_PSW = DCRCDDAT.TRAN_PIN_DAT;
                } else {
                    WS_TXN_PSW = DCCSGTCD.TXN_PSW;
                }
            }
        }
    }
    public void B015_CHECK_CITIZEN_CRD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCTCDC);
        IBS.init(SCCGWA, CICQCHDC);
        CICQCHDC.DATA.N_AGR_NO = DCCSGTCD.CARD_NO;
        CICQCHDC.FUNC = 'O';
        S000_CALL_CIZQCHDC();
        if (pgmRtn) return;
        DCRCTCDC.KEY.OLD_CARD_NO = CICQCHDC.DATA.O_AGR_NO;
        WS_OLD_CARD_NO = CICQCHDC.DATA.O_AGR_NO;
        CEP.TRC(SCCGWA, DCRCTCDC.KEY.OLD_CARD_NO);
        T000_READ_DCTCTCDC();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_REGISTER;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (WS_TBL_FLAG == 'Y') {
            CEP.TRC(SCCGWA, DCRCTCDC.CHG_STS);
            CEP.TRC(SCCGWA, DCRCTCDC.APP_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            if (DCRCTCDC.CHG_STS != '2') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_HANDOVER;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (DCRCTCDC.APP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CD_LOST_BR_NOT_MCH;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
        }
    }
    public void B020_INQ_ORDER_FILE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCSGTCD.CARD_NO;
        DCRCDORD.KEY.EXC_CARD_TMS = WS_CHANGE_TIM;
        T000_READ_DCTCDORD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDORD.CRT_STS != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_STS_MUST_MADE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, DCRCDORD.KEY.CARD_NO);
        CEP.TRC(SCCGWA, DCRCDORD.CRT_STS);
        WS_CARD_PSW = DCRCDORD.TRAN_PIN_DAT;
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSGTCD.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDDAT.CARD_HLDR_CINO);
        if (DCRCDDAT.CARD_HLDR_CINO.trim().length() > 0) {
            WS_CI_NO = DCRCDDAT.CARD_HLDR_CINO;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'C';
            CICCUST.DATA.CI_NO = WS_CI_NO;
            CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_ENM);
            if (CICCUST.O_DATA.O_CI_NM.trim().length() > 0) {
                WS_CI_NM = CICCUST.O_DATA.O_CI_NM;
            } else {
                WS_CI_NM = CICCUST.O_DATA.O_CI_ENM;
            }
        }
        WS_ID_TYPE = CICCUST.O_DATA.O_ID_TYPE;
        WS_ID_NO = CICCUST.O_DATA.O_ID_NO;
    }
    public void B030_UPDATE_ORDER_FILE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = DCCSGTCD.CARD_NO;
        DCRCDORD.KEY.EXC_CARD_TMS = WS_CHANGE_TIM;
        DCRCDORD.CRT_STS = '2';
        T000_READUPD_DCTCDORD();
        if (pgmRtn) return;
        DCRCDORD.CRT_STS = '3';
        DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DCRCDORD.UPDTBL_DATE);
        T000_UPDATE_DCTCDORD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "AAA");
        CEP.TRC(SCCGWA, DCRCDORD.UPDTBL_DATE);
        if (WS_CARD_TYPE == 'Y') {
            B033_CITZEN_CARD();
            if (pgmRtn) return;
        }
    }
    public void B031_SOCIAL_CARD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_SOCIAL_CARD_NO);
        CEP.TRC(SCCGWA, WS_SOCIAL_CARD_SEQ);
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = WS_SOCIAL_CARD_NO;
        T000_READUPD_DCTCDORD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRCDORD.CRT_STS = '3';
        DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDORD();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = WS_SOCIAL_CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (WS_SOCIAL_EVN_FLG == 'Y') {
            DCRCDDAT.TRAN_PIN_DAT = WS_SOCIAL_PSW;
            DCRCDDAT.CARD_STS = 'N';
        }
        DCRCDDAT.LAST_TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.ISSU_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.CLT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B032_FINANCIAL_CARD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_FINANCIAL_CARD_NO);
        CEP.TRC(SCCGWA, WS_FINANCIAL_CARD_SEQ);
        IBS.init(SCCGWA, DCRCDORD);
        DCRCDORD.KEY.CARD_NO = WS_FINANCIAL_CARD_NO;
        T000_READUPD_DCTCDORD();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DCRCDORD.CRT_STS != '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_STS_MUST_MADE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRCDORD.CRT_STS = '3';
        DCRCDORD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDORD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        CEP.TRC(SCCGWA, "BBB");
        CEP.TRC(SCCGWA, DCRCDORD.UPDTBL_DATE);
        T000_UPDATE_DCTCDORD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCRCDORD.UPDTBL_DATE);
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = WS_FINANCIAL_CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRCDDAT.LAST_TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.ISSU_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.CLT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B033_CITZEN_CARD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCTCDC);
        CEP.TRC(SCCGWA, WS_OLD_CARD_NO);
        DCRCTCDC.KEY.OLD_CARD_NO = WS_OLD_CARD_NO;
        T000_READUPD_DCTCTCDC();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRCTCDC.CHG_STS = '3';
        DCRCTCDC.FET_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCTCDC.FET_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCTCDC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCTCDC.FET_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRCTCDC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCTCDC();
        if (pgmRtn) return;
    }
    public void B040_CARD_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSGTCD.CARD_NO;
        CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
        T000_READUPD_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        DCRCDDAT.CARD_STS = '2';
        if (DCCSGTCD.CARD_STS == '0') {
            DCRCDDAT.CARD_STS = 'N';
        }
        DCRCDDAT.LAST_TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.ISSU_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.CLT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRCDDAT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRCDDAT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTCDDAT();
        if (pgmRtn) return;
    }
    public void B025_CARD_VOUCHER() throws IOException,SQLException,Exception {
        WS_VOUCHER_CARD_NO = DCCSGTCD.CARD_NO;
        CEP.TRC(SCCGWA, WS_VOUCHER_CARD_NO);
        IBS.init(SCCGWA, DCRBINPM);
        if (WS_VOUCHER_CARD_NO == null) WS_VOUCHER_CARD_NO = "";
        JIBS_tmp_int = WS_VOUCHER_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) WS_VOUCHER_CARD_NO += " ";
        DCRBINPM.KEY.BIN = WS_VOUCHER_CARD_NO.substring(0, 6);
        T000_READ_DCTBINPM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_PREMARK_CARD;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        if (DCCSGTCD.TXN_TLR.trim().length() > 0) {
            BPCUBUSE.TLR = DCCSGTCD.TXN_TLR;
        }
        CEP.TRC(SCCGWA, DCCSGTCD.TXN_TLR);
        CEP.TRC(SCCGWA, BPCUBUSE.TLR);
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.VB_FLG = '0';
        BPCUBUSE.COUNT_MTH = '1';
        if (WS_VOUCHER_CARD_NO == null) WS_VOUCHER_CARD_NO = "";
        JIBS_tmp_int = WS_VOUCHER_CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) WS_VOUCHER_CARD_NO += " ";
        BPCUBUSE.BEG_NO = WS_VOUCHER_CARD_NO.substring(0, 18);
        BPCUBUSE.END_NO = WS_VOUCHER_CARD_NO.substring(0, 18);
        CEP.TRC(SCCGWA, DCRCDDAT.BV_CD_NO);
        BPCUBUSE.BV_CODE = DCRCDDAT.BV_CD_NO;
        CEP.TRC(SCCGWA, BPCUBUSE.HEAD_NO);
        BPCUBUSE.NUM = 1;
        CEP.TRC(SCCGWA, "------------UBUSE-BEG-NO----------");
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.END_NO);
        CEP.TRC(SCCGWA, BPCUBUSE);
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void B041_GENERATE_PIN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCS9991);
        CEP.TRC(SCCGWA, DCCSGTCD.CARD_NO);
        DCCS9991.OLD_AGR_NO = "123456789012";
        DCCS9991.NEW_AGR_NO = DCCSGTCD.CARD_NO;
        if (DCCSGTCD.CARD_NO == null) DCCSGTCD.CARD_NO = "";
        JIBS_tmp_int = DCCSGTCD.CARD_NO.length();
        for (int i=0;i<19-JIBS_tmp_int;i++) DCCSGTCD.CARD_NO += " ";
        DCCS9991.AGR_NO_6 = DCCSGTCD.CARD_NO.substring(14 - 1, 14 + 6 - 1);
        DCCS9991.ID_TYPE = DCCSGTCD.ID_TYP;
        DCCS9991.ID_NO = DCCSGTCD.ID_NO;
        DCCS9991.CI_NM = DCCSGTCD.CI_NAME;
        DCCS9991.PSWD_NO = WS_TXN_PSW;
        S000_CALL_DCZUPWCK();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DCCS9991.O_PINOFFSET);
    }
    public void B050_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUTPUT);
        WS_OUTPUT.WS_CARD_NO = DCCSGTCD.CARD_NO;
        WS_OUTPUT.WS_CARD_SEQ = DCCSGTCD.CARD_SEQ;
        WS_OUTPUT.WS_HOLDER_IDTYP = WS_ID_TYPE;
        WS_OUTPUT.WS_HOLDER_IDNO = WS_ID_NO;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_HOLDER_IDTYP);
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_HOLDER_IDNO);
        CEP.TRC(SCCGWA, WS_CI_NO);
        WS_OUTPUT.WS_HOLDER_NAME = WS_CI_NM;
        CEP.TRC(SCCGWA, WS_OUTPUT.WS_HOLDER_NAME);
        WS_OUTPUT.WS_LAST_DATE = SCCGWA.COMM_AREA.AC_DATE;
        WS_OUTPUT.WS_LAST_USER = SCCGWA.COMM_AREA.TL_ID;
        WS_OUTPUT.WS_TR_BRANCH = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, WS_OUTPUT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = WS_OUTPUT;
        SCCFMT.DATA_LEN = 371;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B060_HISTORY_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCHISTY);
        IBS.CLONE(SCCGWA, DCCSGTCD, DCCHISTY);
        H000_HISTORY_RECORD();
        if (pgmRtn) return;
    }
    public void H000_HISTORY_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.TX_RMK = "GETTING CARD";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "DCCSGTCD";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TOOL = DCCSGTCD.CARD_NO;
        BPCPNHIS.INFO.CI_NO = DCRCDDAT.CARD_OWN_CINO;
        CEP.TRC(SCCGWA, "THJ TEST:");
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.FMT_ID_LEN = 390;
        BPCPNHIS.INFO.NEW_DAT_PT = DCCHISTY;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_READ_DCTBINPM() throws IOException,SQLException,Exception {
        DCTBINPM_RD = new DBParm();
        DCTBINPM_RD.TableName = "DCTBINPM";
        DCTBINPM_RD.col = "BIN, UNION_SHORT_NAME, UNION_NAME, CHK_DIG_MTH, PIN_MTH, CVV_TYP, CARD_LEN, SEG_NUM, SEG1_LEN, SEG1_RMK, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        IBS.READ(SCCGWA, DCRBINPM, DCTBINPM_RD);
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void S000_CALL_DCZUPWCK() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-SIMPLE-PSW-CHECK", DCCS9991);
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CISOCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DCZUPSWM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-PSW-MAINTAIN", DCCUPSWM);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, DCRCDDAT.KEY.CARD_NO);
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
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
    public void T000_STARTBR_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.fst = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
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
    public void T000_READUPD_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        DCTCDDAT_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
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
    public void T000_UPDATE_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.REWRITE(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
    }
    public void T000_READ_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCDORD() throws IOException,SQLException,Exception {
        null.col = "CARD_NO, CARD_PROD, EMBS_TYP, APP_BAT_NO, CRT_STS, CRT_DT, TRAN_PIN_DAT, QURY_PIN_DAT, APP_TELLER, APP_DT, APP_BR, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.upd = true;
        IBS.READ(SCCGWA, DCRCDORD, DCTCDORD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDORD";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCDORD() throws IOException,SQLException,Exception {
        DCTCDORD_RD = new DBParm();
        DCTCDORD_RD.TableName = "DCTCDORD";
        DCTCDORD_RD.col = "CARD_PROD, EMBS_TYP, APP_BAT_NO, CRT_STS, CRT_DT, TRAN_PIN_DAT, QURY_PIN_DAT, APP_TELLER, APP_DT, APP_BR, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTCDORD_RD.where = "CARD_NO = :DCRCDORD.KEY.CARD_NO "
            + "AND EXC_CARD_TMS = :DCRCDORD.KEY.EXC_CARD_TMS";
        IBS.REWRITE(SCCGWA, DCRCDORD, this, DCTCDORD_RD);
    }
    public void T000_READ_DCTINRCD() throws IOException,SQLException,Exception {
        DCTINRCD_RD = new DBParm();
        DCTINRCD_RD.TableName = "DCTINRCD";
        DCTINRCD_RD.col = "CARD_NO, CARD_SEQ, TR_JRNNO, INCD_TYPE, TR_BR, AUT_TLR, CRT_DATE, CRT_TLR, UPDTBL_DATE, UPDTBL_TLR";
        DCTINRCD_RD.where = "CARD_NO = :DCRINRCD.KEY.CARD_NO "
            + "AND CARD_SEQ = :DCRINRCD.KEY.CARD_SEQ "
            + "AND INCD_TYPE = '02'";
        DCTINRCD_RD.fst = true;
        DCTINRCD_RD.order = "CRT_DATE DESC, TS DESC";
        IBS.READ(SCCGWA, DCRINRCD, this, DCTINRCD_RD);
    }
    public void T000_READ_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        DCTCTCDC_RD.where = "OLD_CARD_NO = :DCRCTCDC.KEY.OLD_CARD_NO";
        IBS.READ(SCCGWA, DCRCTCDC, this, DCTCTCDC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCTCDC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        DCTCTCDC_RD.upd = true;
        IBS.READ(SCCGWA, DCRCTCDC, DCTCTCDC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCTCDC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_DCTCTCDC() throws IOException,SQLException,Exception {
        DCTCTCDC_RD = new DBParm();
        DCTCTCDC_RD.TableName = "DCTCTCDC";
        DCTCTCDC_RD.col = "CHG_STS , FET_BR , FET_DT , FET_TLR";
        DCTCTCDC_RD.where = "OLD_CARD_NO = :DCRCTCDC.KEY.OLD_CARD_NO";
        IBS.REWRITE(SCCGWA, DCRCTCDC, this, DCTCTCDC_RD);
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
        if (CICQCHDC.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQCHDC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS         ", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
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
