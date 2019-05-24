package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZRATMT {
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTMST_RD;
    DBParm IBTINRH_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_IBTMST = "IBTMST  ";
    String K_IBTINRH = "IBTINRH ";
    short K_360_DAYS = 360;
    short K_365_DAYS = 365;
    short K_366_DAYS = 366;
    String K_OUTPUT_FMT = "IBB11";
    short WS_I = 0;
    char WS_TABLE_REC = ' ';
    char WS_TXNBR_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCQINF IBCQINF = new IBCQINF();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBCNMINT IBCMINTO = new IBCNMINT();
    IBCNMINT IBCMINTN = new IBCNMINT();
    BPCCINTI BPCCINTI = new BPCCINTI();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    IBCSOINT IBCSOINT = new IBCSOINT();
    IBRMST IBRMST = new IBRMST();
    IBRINRH IBRINRH = new IBRINRH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCRATMT IBCRATMT;
    public void MP(SCCGWA SCCGWA, IBCRATMT IBCRATMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCRATMT = IBCRATMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZRATMT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
        IBCRATMT.RC.RC_MMO = " ";
        IBCRATMT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_AC_INFO_UPD();
        if (pgmRtn) return;
        B030_WRITE_HIS_PROC();
        if (pgmRtn) return;
        B040_WRITE_INRH_PROC();
        if (pgmRtn) return;
        if (IBCRATMT.EFFDATE <= SCCGWA.COMM_AREA.AC_DATE) {
            B050_MODIFY_PROCESS();
            if (pgmRtn) return;
        }
        B060_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCRATMT.AC_NO);
        CEP.TRC(SCCGWA, IBCRATMT.NOS_CD);
        CEP.TRC(SCCGWA, IBCRATMT.CCY);
        if ((IBCRATMT.NOS_CD.trim().length() == 0 
            || IBCRATMT.CCY.trim().length() == 0) 
            && IBCRATMT.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBCRATMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B010_01_GET_AC_INFO();
        if (pgmRtn) return;
        if (IBCQINF.OUTPUT_DATA.POST_ACT_CTR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
            B010_02_CHECK_TXNBR();
            if (pgmRtn) return;
        }
        B010_03_CHECK_EFFDATE();
        if (pgmRtn) return;
        B010_04_CHECK_INPUT();
        if (pgmRtn) return;
    }
    public void B010_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        if (IBCRATMT.AC_NO.trim().length() > 0) {
            IBCQINF.INPUT_DATA.AC_NO = IBCRATMT.AC_NO;
        } else {
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBCRATMT.NOS_CD;
            IBCQINF.INPUT_DATA.CCY = IBCRATMT.CCY;
        }
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        if (IBCQINF.OUTPUT_DATA.AC_STS != 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL, IBCRATMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_02_CHECK_TXNBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, IBCPMORG);
        IBCPMORG.KEY.TYP = "PIB09";
        IBCPMORG.KEY.CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPRMR.DAT_PTR = IBCPMORG;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCPMORG.DATA_TXT.CTL_TYP);
        if (IBCPMORG.DATA_TXT.CTL_TYP == '0') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR, IBCRATMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (IBCPMORG.DATA_TXT.CTL_TYP == '1') {
            for (WS_I = 1; WS_TXNBR_FLAG != 'Y' 
                && WS_I <= 10; WS_I += 1) {
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == IBCPMORG.DATA_TXT.BR[WS_I-1]) {
                    WS_TXNBR_FLAG = 'Y';
                }
            }
            if (WS_TXNBR_FLAG != 'Y') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR, IBCRATMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
        }
    }
    public void B010_03_CHECK_EFFDATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINRH);
        IBRINRH.KEY.AC_NO = IBCRATMT.AC_NO;
        T000_READ_INRH_LAST1();
        if (pgmRtn) return;
        if ((IBCQINF.OUTPUT_DATA.L_INTS_DATE != 0 
            && IBCRATMT.EFFDATE < IBCQINF.OUTPUT_DATA.L_INTS_DATE)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.IB_DTE_LESS_JXD, IBCRATMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((IBCQINF.OUTPUT_DATA.L_INTS_DATE == 0 
            && IBCRATMT.EFFDATE < IBCQINF.OUTPUT_DATA.EFF_DATE)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.IB_EFF_DTE_LESS_ACDT, IBCRATMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCRATMT.EFFDATE < IBRINRH.KEY.VALUE_DATE) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.EFFDATE_LE_VALUE, IBCRATMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_04_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCRATMT.RATE_MTH);
        CEP.TRC(SCCGWA, IBCRATMT.RATE);
        CEP.TRC(SCCGWA, IBCRATMT.RATE_TYPE);
        CEP.TRC(SCCGWA, IBCRATMT.RATE_TERM);
        CEP.TRC(SCCGWA, IBCRATMT.CALR_STD);
        if (IBCQINF.OUTPUT_DATA.RATE_FLAG == 'Y') {
            if (IBCRATMT.EFFDATE == 0) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.EFFDATE_M, IBCRATMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (IBCRATMT.RATE_MTH == '1') {
            } else if (IBCRATMT.RATE_MTH == '2') {
                if (IBCRATMT.RATE_TYPE.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.RATE_TYPE_M, IBCRATMT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                if (IBCRATMT.RATE_TERM.trim().length() == 0) {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.RATE_TERM_M, IBCRATMT.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                B010_04_01_CHECK_RATE();
                if (pgmRtn) return;
            } else {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.RATE_MTH_M, IBCRATMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            if (IBCRATMT.CALR_STD == 0) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CALR_STD_M, IBCRATMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B010_04_01_CHECK_RATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCCINTI);
        BPCCINTI.FUNC = 'I';
        BPCCINTI.BASE_INFO.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        BPCCINTI.BASE_INFO.CCY = IBCRATMT.CCY;
        BPCCINTI.BASE_INFO.BASE_TYP = IBCRATMT.RATE_TYPE;
        BPCCINTI.BASE_INFO.TENOR = IBCRATMT.RATE_TERM;
        BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
        S00_CALL_BPZCINTI();
        if (pgmRtn) return;
    }
    public void B020_GET_AC_INFO_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCRATMT.AC_NO;
        T000_READ_IBTMST_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCRATMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_WRITE_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINRH);
        IBRINRH.KEY.AC_NO = IBCRATMT.AC_NO;
        T000_READ_INRH_LAST2();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBCMINTO);
        IBCMINTO.AC_NO = IBCRATMT.AC_NO;
        IBCMINTO.NOST_CODE = IBRMST.NOSTRO_CODE;
        IBCMINTO.CCY = IBRMST.CCY;
        IBCMINTO.RATE_MTH = IBRMST.RATE_MTH;
        IBCMINTO.RATE_TYPE = IBRMST.RATE_TYPE;
        IBCMINTO.RATE_TERM = IBRMST.RATE_TERM;
        IBCMINTO.RATE_PCT = IBRMST.RATE_PCT;
        IBCMINTO.RATE_SPREAD = IBRMST.RATE_SPREAD;
        IBCMINTO.CALR_STD = IBRMST.CALR_STD;
        IBCMINTO.RATE = IBRMST.RATE;
        IBCMINTO.EFFDATE = IBRINRH.KEY.VALUE_DATE;
        IBCMINTO.UPD_DATE = IBRINRH.UPD_DATE;
        IBCMINTO.UPD_TLR = IBRINRH.UPD_TLR;
        IBS.init(SCCGWA, IBCMINTN);
        IBCMINTN.AC_NO = IBCRATMT.AC_NO;
        IBCMINTN.NOST_CODE = IBRMST.NOSTRO_CODE;
        IBCMINTN.CCY = IBRMST.CCY;
        IBCMINTN.RATE_MTH = IBCRATMT.RATE_MTH;
        IBCMINTN.RATE_TYPE = IBCRATMT.RATE_TYPE;
        IBCMINTN.RATE_TERM = IBCRATMT.RATE_TERM;
        IBCMINTN.RATE_PCT = IBCRATMT.RATE_PCT;
        IBCMINTN.RATE_SPREAD = IBCRATMT.RATE_SPREAD;
        IBCMINTN.CALR_STD = IBCRATMT.CALR_STD;
        IBCMINTN.RATE = IBCRATMT.RATE;
        IBCMINTN.EFFDATE = IBCRATMT.EFFDATE;
        IBCMINTN.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBCMINTN.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = IBCRATMT.AC_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        BPCPNHIS.INFO.CCY = IBCQINF.INPUT_DATA.CCY;
        if (IBCQINF.INPUT_DATA.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.TX_TYP_CD = "P704";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "IBCNMINT";
        BPCPNHIS.INFO.FMT_ID_LEN = 131;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = IBCMINTO;
        BPCPNHIS.INFO.NEW_DAT_PT = IBCMINTN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B030_01_GET_LAST_UPD_DATE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINRH);
        IBRINRH.KEY.AC_NO = IBCRATMT.AC_NO;
        CEP.TRC(SCCGWA, IBRINRH.KEY.AC_NO);
        T000_READ_INRH_LAST2();
        if (pgmRtn) return;
    }
    public void B040_WRITE_INRH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINRH);
        IBRINRH.KEY.AC_NO = IBCRATMT.AC_NO;
        IBRINRH.KEY.VALUE_DATE = IBCRATMT.EFFDATE;
        CEP.TRC(SCCGWA, IBRINRH.KEY.VALUE_DATE);
        T000_READ_IBTINRH_UPD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBZRATMT_WS2);
        if (WS_TABLE_REC == 'N') {
            IBS.init(SCCGWA, IBRINRH);
            IBRINRH.KEY.AC_NO = IBCRATMT.AC_NO;
            IBRINRH.KEY.VALUE_DATE = IBCRATMT.EFFDATE;
            IBRINRH.PRV_FLAG = IBRMST.PRV_FLAG;
            IBRINRH.RATE_FLAG = IBRMST.RATE_FLAG;
            IBRINRH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINRH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        }
        IBRINRH.RATE_MTH = IBCRATMT.RATE_MTH;
        IBRINRH.RATE_TYPE = IBCRATMT.RATE_TYPE;
        IBRINRH.RATE_TERM = IBCRATMT.RATE_TERM;
        IBRINRH.RATE_PCT = IBCRATMT.RATE_PCT;
        IBRINRH.RATE_SPREAD = IBCRATMT.RATE_SPREAD;
        if (IBCRATMT.RATE_MTH == '1') {
            IBRINRH.INT_RATE = IBCRATMT.RATE;
        } else {
            if (IBRINRH.RATE_SPREAD != 0) {
                IBRINRH.INT_RATE = BPCCINTI.BASE_INFO.RATE + IBRINRH.RATE_SPREAD;
            } else {
                IBRINRH.INT_RATE = BPCCINTI.BASE_INFO.RATE * ( 1 + IBRINRH.RATE_PCT / 100 );
            }
        }
        IBRINRH.CALR_STD = IBCRATMT.CALR_STD;
        IBRINRH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRINRH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRINRH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        if (WS_TABLE_REC == 'N') {
            T000_WRITE_IBTINRH();
            if (pgmRtn) return;
        } else {
            T000_REWRITE_IBTINRH();
            if (pgmRtn) return;
        }
    }
    public void B050_MODIFY_PROCESS() throws IOException,SQLException,Exception {
        IBRMST.RATE_MTH = IBCRATMT.RATE_MTH;
        IBRMST.RATE_TYPE = IBCRATMT.RATE_TYPE;
        IBRMST.RATE_TERM = IBCRATMT.RATE_TERM;
        IBRMST.RATE_PCT = IBCRATMT.RATE_PCT;
        IBRMST.RATE_SPREAD = IBCRATMT.RATE_SPREAD;
        IBRMST.CALR_STD = IBCRATMT.CALR_STD;
        IBRMST.RATE = IBCRATMT.RATE;
        IBRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_IBTMST();
        if (pgmRtn) return;
    }
    public void B060_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCSOINT);
        IBCSOINT.DATA.NOST_CODE = IBCQINF.INPUT_DATA.NOSTRO_CD;
        IBCSOINT.DATA.CCY = IBCQINF.INPUT_DATA.CCY;
        IBCSOINT.DATA.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        IBCSOINT.DATA.CUSTNAME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        IBCSOINT.DATA.EFFDATE = IBCRATMT.EFFDATE;
        IBCSOINT.DATA.PRV_FLAG = IBCQINF.OUTPUT_DATA.PRV_FLAG;
        IBCSOINT.DATA.RATE_FLAG = IBCQINF.OUTPUT_DATA.RATE_FLAG;
        IBCSOINT.DATA.RATE_MTH = IBRMST.RATE_MTH;
        IBCSOINT.DATA.RATE_TYPE = IBRMST.RATE_TYPE;
        IBCSOINT.DATA.RATE_TERM = IBRMST.RATE_TERM;
        IBCSOINT.DATA.RATE_PCT = IBRMST.RATE_PCT;
        IBCSOINT.DATA.RATE_SPREAD = IBRMST.RATE_SPREAD;
        IBCSOINT.DATA.RATE = IBRMST.RATE;
        IBCSOINT.DATA.OD_RATE = IBCQINF.OUTPUT_DATA.OD_RATE;
        if (IBRMST.CALR_STD == K_360_DAYS) {
            IBCSOINT.DATA.CALR_STD = "01";
        } else if (IBRMST.CALR_STD == K_365_DAYS) {
            IBCSOINT.DATA.CALR_STD = "02";
        } else if (IBRMST.CALR_STD == K_366_DAYS) {
            IBCSOINT.DATA.CALR_STD = "03";
        }
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCSOINT;
        SCCFMT.DATA_LEN = 384;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S00_CALL_BPZCINTI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-C-INTR-INQ", BPCCINTI);
        if (BPCCINTI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCCINTI.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCRATMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCRATMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCRATMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCRATMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMST_UPD() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.REWRITE(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_INRH_LAST1() throws IOException,SQLException,Exception {
        IBTINRH_RD = new DBParm();
        IBTINRH_RD.TableName = "IBTINRH";
        IBTINRH_RD.where = "AC_NO = :IBRINRH.KEY.AC_NO";
        IBTINRH_RD.fst = true;
        IBTINRH_RD.order = "VALUE_DATE DESC";
        IBS.READ(SCCGWA, IBRINRH, this, IBTINRH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTINRH";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_INRH_LAST2() throws IOException,SQLException,Exception {
        IBTINRH_RD = new DBParm();
        IBTINRH_RD.TableName = "IBTINRH";
        IBTINRH_RD.where = "AC_NO = :IBRINRH.KEY.AC_NO";
        IBTINRH_RD.fst = true;
        IBTINRH_RD.order = "CRT_DATE DESC";
        IBS.READ(SCCGWA, IBRINRH, this, IBTINRH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINRH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTINRH_UPD() throws IOException,SQLException,Exception {
        IBTINRH_RD = new DBParm();
        IBTINRH_RD.TableName = "IBTINRH";
        IBTINRH_RD.upd = true;
        IBS.READ(SCCGWA, IBRINRH, IBTINRH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINRH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTINRH() throws IOException,SQLException,Exception {
        IBTINRH_RD = new DBParm();
        IBTINRH_RD.TableName = "IBTINRH";
        IBS.WRITE(SCCGWA, IBRINRH, IBTINRH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.REC_DUPKEY, IBCRATMT.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINRH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTINRH() throws IOException,SQLException,Exception {
        IBTINRH_RD = new DBParm();
        IBTINRH_RD.TableName = "IBTINRH";
        IBS.REWRITE(SCCGWA, IBRINRH, IBTINRH_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = K_IBTINRH;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
