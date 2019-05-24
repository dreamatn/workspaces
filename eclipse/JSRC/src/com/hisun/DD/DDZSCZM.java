package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.DC.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSCZM {
    brParm DDTCCZM_BR = new brParm();
    DBParm DDTCCZM_RD;
    brParm DDTZMAC_BR = new brParm();
    DBParm DDTZMAC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_CODE = "DD153";
    String WS_OPEN_BV = " ";
    String WS_HLD_NO = " ";
    String WS_REF_NO = " ";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    char WS_FLAG = ' ';
    String WS_TMP_HLD_NO = " ";
    String WS_TMP_AC = " ";
    String WS_B_CCY = " ";
    double WS_TOTAL_AMT = 0;
    String WS_CI_NO = " ";
    int WS_BANK_NO = 0;
    int WS_CNT_T = 0;
    DDZSCZM_WS_AC_LIST[] WS_AC_LIST = new DDZSCZM_WS_AC_LIST[6];
    char WS_DDTCCZM_REC = ' ';
    char WS_DDTZMAC_REC = ' ';
    char WS_HLD_REC = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDRCCZM DDRCCZM = new DDRCCZM();
    DDRZMAC DDRZMAC = new DDRZMAC();
    DCCURHLD DCCURHLD = new DCCURHLD();
    BPCUBUSE BPCUBUSE = new BPCUBUSE();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCOCZM DDCOCZM = new DDCOCZM();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    DCCURHOL DCCURHOL = new DCCURHOL();
    SCCGWA SCCGWA;
    DDCSCZM DDCSCZM;
    public DDZSCZM() {
        for (int i=0;i<6;i++) WS_AC_LIST[i] = new DDZSCZM_WS_AC_LIST();
    }
    public void MP(SCCGWA SCCGWA, DDCSCZM DDCSCZM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSCZM = DDCSCZM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSCZM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCSCZM.INPUT_DATA.IZM_FUNC);
        if (DDCSCZM.INPUT_DATA.IZM_FUNC == '1') {
            B030_CANCEL_CCZM();
            if (pgmRtn) return;
        } else {
            if (DDCSCZM.INPUT_DATA.IZM_FUNC == '2') {
                B050_CHANGE_CCZM();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_INVALID;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        for (WS_CNT = 1; WS_CNT <= WS_CNT_T; WS_CNT += 1) {
            B230_WRI_NFIN_HIS_PROC();
            if (pgmRtn) return;
        }
        B070_FMT_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (DDCSCZM.INPUT_DATA.IZM_OPEN_BV.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_BV_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSCZM.INPUT_DATA.IZM_FUNC == '2' 
            && DDCSCZM.INPUT_DATA.IZM_NEW_BV.trim().length() == 0) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_NEW_BV_NULL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B230_WRI_NFIN_HIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'O';
        BPCPNHIS.INFO.TX_TYP_CD = "P123";
        if (DDCSCZM.INPUT_DATA.IZM_FUNC == '1') {
            BPCPNHIS.INFO.AC = WS_AC_LIST[WS_CNT-1].WS_AC;
            BPCPNHIS.INFO.AC_SEQ = WS_AC_LIST[WS_CNT-1].WS_AC_SEQ;
            BPCPNHIS.INFO.CCY = WS_AC_LIST[WS_CNT-1].WS_CCY;
            BPCPNHIS.INFO.CCY_TYPE = WS_AC_LIST[WS_CNT-1].WS_CCY_TYPE;
        }
        BPCPNHIS.INFO.TX_RMK = "存款证明取消";
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CI_NO = DDRCCZM.CI_NO;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B030_CANCEL_CCZM() throws IOException,SQLException,Exception {
        B030_01_UPD_CCZM_STS();
        if (pgmRtn) return;
        B030_02_UPD_ZMAC_STS();
        if (pgmRtn) return;
        B030_WRITE_VCH_PROC();
        if (pgmRtn) return;
        DDCOCZM.OUTPUT_DATA.OZM_BV_STS = 'C';
    }
    public void B050_CHANGE_CCZM() throws IOException,SQLException,Exception {
        B050_01_WRITE_CCZM();
        if (pgmRtn) return;
        B050_02_WRITE_ZMAC();
        if (pgmRtn) return;
        B050_03_DELETE_OLD_RECORD();
        if (pgmRtn) return;
        DDCSCZM.OUTPUT_DATA.OZM_BV_STS = 'N';
    }
    public void B050_03_DELETE_OLD_RECORD() throws IOException,SQLException,Exception {
        T000_DELETE_DDTZMAC();
        if (pgmRtn) return;
        T000_DELETE_DDTCCZM();
        if (pgmRtn) return;
    }
    public void B030_01_UPD_CCZM_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCZM);
        DDRCCZM.KEY.OPEN_BV_CODE = "C00008";
        DDRCCZM.KEY.OPEN_BV = DDCSCZM.INPUT_DATA.IZM_OPEN_BV;
        CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV_CODE);
        CEP.TRC(SCCGWA, DDRCCZM.KEY.REF_NO);
        CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV);
        T000_READ_DDTCCZM();
        if (pgmRtn) return;
        if (WS_DDTCCZM_REC == 'N') {
            CEP.TRC(SCCGWA, "---DDTCCZM NOT FOUND---");
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DDTCCZM_OPENBV_NO_EXIT);
        }
        if (WS_DDTCCZM_REC == 'Y') {
            if (DDRCCZM.BAL_TYPE == '2') {
                CEP.TRC(SCCGWA, "---TIME POINT CERTIFICATE---");
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.TIMEPOINT_CERT_NCANCLE);
            }
            if (DDRCCZM.STS == 'C') {
                CEP.TRC(SCCGWA, "---CCZM HAS BEEN CANCEL---");
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_CCZM_REC_ALR_CANCEL);
            }
            if (DDRCCZM.STS == 'R') {
                CEP.TRC(SCCGWA, "---REOPEN CCZM CANT CANCEL---");
                CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_REOP_CCZM_CANT_CANC);
            }
        }
        WS_REF_NO = DDRCCZM.KEY.REF_NO;
        CEP.TRC(SCCGWA, DDRCCZM.KEY.REF_NO);
        IBS.init(SCCGWA, DDRCCZM);
        T000_STARTBR_DDTCCZM();
        if (pgmRtn) return;
        T000_READNXT_DDTCCZM();
        if (pgmRtn) return;
        while (WS_DDTCCZM_REC != 'N') {
            DDRCCZM.STS = 'C';
            CEP.TRC(SCCGWA, "F-REWRITE-CCZM");
            CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV_CODE);
            CEP.TRC(SCCGWA, DDRCCZM.KEY.OPEN_BV);
            CEP.TRC(SCCGWA, DDRCCZM.STS);
            T00_REWRITE_DDTCCZM();
            if (pgmRtn) return;
            T000_READNXT_DDTCCZM();
            if (pgmRtn) return;
        }
        WS_TOTAL_AMT = DDRCCZM.TOTAL_OPEN_AMT;
        WS_CI_NO = DDRCCZM.CI_NO;
        WS_BANK_NO = DDRCCZM.BANK_NO;
        CEP.TRC(SCCGWA, WS_TOTAL_AMT);
        CEP.TRC(SCCGWA, WS_CI_NO);
        CEP.TRC(SCCGWA, WS_BANK_NO);
        T000_ENDBR_DDTCCZM();
        if (pgmRtn) return;
    }
    public void B030_02_UPD_ZMAC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRZMAC);
        CEP.TRC(SCCGWA, DDRCCZM.KEY.REF_NO);
        T000_STARTBR_DDTZMAC_UPD();
        if (pgmRtn) return;
        WS_FLAG = ' ';
        while (WS_FLAG != 'N') {
            T000_READNEXT_DDTZMAC();
            if (pgmRtn) return;
            if (WS_DDTZMAC_REC == 'Y') {
                DDRZMAC.HLD_STS = 'C';
                T00_REWRITE_DDTZMAC();
                if (pgmRtn) return;
                B030_031_RHLD_PROC();
                if (pgmRtn) return;
                WS_CNT_T += 1;
                WS_AC_LIST[WS_CNT_T-1].WS_AC = DDRZMAC.AC;
                WS_AC_LIST[WS_CNT_T-1].WS_AC_SEQ = DDRZMAC.AC_SEQ;
                WS_AC_LIST[WS_CNT_T-1].WS_CCY = DDRZMAC.CCY;
                WS_AC_LIST[WS_CNT_T-1].WS_CCY_TYPE = DDRZMAC.CCY_TYPE;
            } else {
                WS_FLAG = 'N';
            }
        }
        CEP.TRC(SCCGWA, DDRZMAC.AC);
        WS_B_CCY = WS_AC_LIST[1-1].WS_CCY;
        CEP.TRC(SCCGWA, WS_B_CCY);
        T000_ENDBR_DDTZMAC();
        if (pgmRtn) return;
    }
    public void B030_03_ZMAC_RHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRZMAC);
        WS_OPEN_BV = DDCSCZM.INPUT_DATA.IZM_OPEN_BV;
        T000_STARTBR_DDTZMAC();
        if (pgmRtn) return;
        WS_FLAG = ' ';
        while (WS_FLAG != 'N') {
            T000_READNEXT_DDTZMAC();
            if (pgmRtn) return;
            if (WS_DDTZMAC_REC == 'Y') {
                B030_031_RHLD_PROC();
                if (pgmRtn) return;
            } else {
                WS_FLAG = 'N';
            }
        }
        T000_ENDBR_DDTZMAC();
        if (pgmRtn) return;
    }
    public void B030_031_RHLD_PROC() throws IOException,SQLException,Exception {
        if (DDRZMAC.HLD_NO.trim().length() > 0) {
            WS_TMP_HLD_NO = DDRZMAC.HLD_NO;
            WS_TMP_AC = DDRZMAC.AC;
            WS_HLD_NO = DDRZMAC.HLD_NO;
            CEP.TRC(SCCGWA, WS_TMP_HLD_NO);
            CEP.TRC(SCCGWA, WS_TMP_AC);
            IBS.init(SCCGWA, DCCURHOL);
            DCCURHOL.DATA.HLD_NO = WS_TMP_HLD_NO;
            DCCURHOL.DATA.AC = WS_TMP_AC;
            DCCURHOL.DATA.RHLD_TYP = '1';
            DCCURHOL.DATA.HLD_TYP = '2';
            DCCURHOL.DATA.SPR_TYP = '2';
            DCCURHOL.DATA.RAMT = DDRZMAC.OPEN_AMT;
            DCCURHOL.DATA.RSN = "存款证明解冻";
            S000_CALL_DCZURHOL();
            if (pgmRtn) return;
        }
    }
    public void B030_03_WRITE_VCH_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = "BVF";
        BPCPOEWA.DATA.PROD_CODE = "9710000002";
        BPCPOEWA.DATA.EVENT_CODE = "CR";
        BPCPOEWA.DATA.BR_OLD = WS_BANK_NO;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = WS_B_CCY;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = WS_TOTAL_AMT;
        BPCPOEWA.DATA.CI_NO = WS_CI_NO;
        S000_CALL_BPZPOEWA();
        if (pgmRtn) return;
    }
    public void B030_WRITE_VCH_PROC() throws IOException,SQLException,Exception {
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPOEWA.RC);
        }
    }
    public void S000_CALL_DCZURHLD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHLD", DCCURHLD);
    }
    public void S000_CALL_DCZURHOL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-UNIT-RHOL", DCCURHOL);
    }
    public void B050_01_WRITE_CCZM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCZM);
        DDRCCZM.KEY.OPEN_BV_CODE = DDCSCZM.IZM_BV_CODE;
        DDRCCZM.KEY.REF_NO = DDCSCZM.INPUT_DATA.IZM_OPEN_BV;
        DDRCCZM.KEY.OPEN_BV = DDCSCZM.INPUT_DATA.IZM_OPEN_BV;
        T000_READ_DDTCCZM();
        if (pgmRtn) return;
        if (WS_DDTCCZM_REC == 'N') {
            CEP.TRC(SCCGWA, "---DDTCCZM NOT FOUND---");
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DDTCCZM_OPENBV_NO_EXIT);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, DDRCCZM.BAL_TYPE);
        CEP.TRC(SCCGWA, DDRCCZM.BAL_EXPD);
        CEP.TRC(SCCGWA, DDRCCZM.BAL_DATE);
        if (DDRCCZM.BAL_TYPE == '1') {
            if (DDRCCZM.BAL_EXPD < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_DT_EXP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        } else {
            if (DDRCCZM.BAL_DATE < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_DT_EXP;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        DDRCCZM.KEY.OPEN_BV = DDCSCZM.INPUT_DATA.IZM_NEW_BV;
        T00_WRITE_DDTCCZM();
        if (pgmRtn) return;
        B200_BV_USE();
        if (pgmRtn) return;
    }
    public void B050_02_WRITE_ZMAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRZMAC);
        WS_OPEN_BV = DDCSCZM.INPUT_DATA.IZM_OPEN_BV;
        T000_STARTBR_DDTZMAC();
        if (pgmRtn) return;
        WS_FLAG = ' ';
        while (WS_FLAG != 'N') {
            T000_READNEXT_DDTZMAC();
            if (pgmRtn) return;
            if (WS_DDTZMAC_REC == 'Y') {
                T00_WRITE_DDTZMAC();
                if (pgmRtn) return;
            } else {
                WS_FLAG = 'N';
            }
        }
        T000_ENDBR_DDTZMAC();
        if (pgmRtn) return;
    }
    public void B200_BV_USE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "F-BV-USE");
        IBS.init(SCCGWA, BPCUBUSE);
        BPCUBUSE.TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCUBUSE.TYPE = '0';
        BPCUBUSE.EC_IND = '1';
        BPCUBUSE.BV_CODE = "00055";
        BPCUBUSE.BEG_NO = DDRCCZM.KEY.OPEN_BV;
        BPCUBUSE.END_NO = DDRCCZM.KEY.OPEN_BV;
        BPCUBUSE.NUM = 1;
        BPCUBUSE.COUNT_MTH = '1';
        BPCUBUSE.VB_FLG = '0';
        CEP.TRC(SCCGWA, BPCUBUSE.BV_CODE);
        CEP.TRC(SCCGWA, BPCUBUSE.BEG_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.END_NO);
        CEP.TRC(SCCGWA, BPCUBUSE.NUM);
        CEP.TRC(SCCGWA, BPCUBUSE.COUNT_MTH);
        CEP.TRC(SCCGWA, BPCUBUSE.VB_FLG);
        S000_CALL_BPZUBUSE();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZUBUSE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-TLR-BV-USE", BPCUBUSE);
    }
    public void B070_FMT_OUTPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CODE;
        SCCFMT.DATA_PTR = DDCOCZM;
        SCCFMT.DATA_LEN = 1;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_STARTBR_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_BR.rp = new DBParm();
        DDTCCZM_BR.rp.TableName = "DDTCCZM";
        DDTCCZM_BR.rp.where = "REF_NO = :WS_REF_NO";
        DDTCCZM_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
    }
    public void T000_READNXT_DDTCCZM() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRCCZM, this, DDTCCZM_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTCCZM_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCZM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        }
    }
    public void T000_ENDBR_DDTCCZM() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTCCZM_BR);
    }
    public void T000_READ_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        DDTCCZM_RD.where = "OPEN_BV_CODE = 'C00008' "
            + "AND OPEN_BV = :DDRCCZM.KEY.OPEN_BV";
        IBS.READ(SCCGWA, DDRCCZM, this, DDTCCZM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTCCZM_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTCCZM_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCZM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        DDTCCZM_RD.where = "OPEN_BV_CODE = '00055' "
            + "AND OPEN_BV = :WS_OPEN_BV";
        IBS.DELETE(SCCGWA, DDRCCZM, this, DDTCCZM_RD);
    }
    public void T00_REWRITE_DDTCCZM() throws IOException,SQLException,Exception {
        DDRCCZM.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCZM.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        IBS.REWRITE(SCCGWA, DDRCCZM, DDTCCZM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCZM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_WRITE_DDTCCZM() throws IOException,SQLException,Exception {
        DDTCCZM_RD = new DBParm();
        DDTCCZM_RD.TableName = "DDTCCZM";
        IBS.WRITE(SCCGWA, DDRCCZM, DDTCCZM_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_DUPKEY;
            CEP.ERR(SCCGWA, WS_ERR_MSG, DDRCCZM.KEY.OPEN_BV);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTCCZM";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTZMAC_UPD() throws IOException,SQLException,Exception {
        DDTZMAC_BR.rp = new DBParm();
        DDTZMAC_BR.rp.TableName = "DDTZMAC";
        DDTZMAC_BR.rp.where = "REF_NO = :DDRCCZM.KEY.REF_NO";
        DDTZMAC_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, DDRZMAC, this, DDTZMAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTZMAC_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTZMAC_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTZMAC() throws IOException,SQLException,Exception {
        DDTZMAC_BR.rp = new DBParm();
        DDTZMAC_BR.rp.TableName = "DDTZMAC";
        DDTZMAC_BR.rp.where = "OPEN_BV_CODE = '00055' "
            + "AND OPEN_BV = :WS_OPEN_BV";
        IBS.STARTBR(SCCGWA, DDRZMAC, this, DDTZMAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTZMAC_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTZMAC_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_DDTZMAC() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DDRZMAC, this, DDTZMAC_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_DDTZMAC_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_DDTZMAC_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_DDTZMAC() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, DDTZMAC_BR);
    }
    public void T00_REWRITE_DDTZMAC() throws IOException,SQLException,Exception {
        DDRZMAC.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRZMAC.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        DDTZMAC_RD = new DBParm();
        DDTZMAC_RD.TableName = "DDTZMAC";
        IBS.REWRITE(SCCGWA, DDRZMAC, DDTZMAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_DDTZMAC() throws IOException,SQLException,Exception {
        DDTZMAC_RD = new DBParm();
        DDTZMAC_RD.TableName = "DDTZMAC";
        DDTZMAC_RD.where = "OPEN_BV_CODE = '00055' "
            + "AND OPEN_BV = :WS_OPEN_BV";
        IBS.DELETE(SCCGWA, DDRZMAC, this, DDTZMAC_RD);
    }
    public void T00_WRITE_DDTZMAC() throws IOException,SQLException,Exception {
        DDTZMAC_RD = new DBParm();
        DDTZMAC_RD.TableName = "DDTZMAC";
        IBS.WRITE(SCCGWA, DDRZMAC, DDTZMAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCZM_DUPKEY;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_DDTZMAC_HLD_FIRST() throws IOException,SQLException,Exception {
        DDTZMAC_RD = new DBParm();
        DDTZMAC_RD.TableName = "DDTZMAC";
        DDTZMAC_RD.where = "HLD_NO = :WS_HLD_NO "
            + "AND HLD_STS = 'N'";
        DDTZMAC_RD.fst = true;
        IBS.READ(SCCGWA, DDRZMAC, this, DDTZMAC_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_HLD_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_HLD_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DDTZMAC";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
