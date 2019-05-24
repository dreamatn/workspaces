package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACMTS {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTTMST_RD;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBE12   ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_TABLE_NAME = " ";
    short WS_I = 0;
    double WS_EXP_INT = 0;
    char WS_TABLE_REC = ' ';
    char WS_TXNBR_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    IBCOCINS IBCOCINS = new IBCOCINS();
    IBCQINFS IBCQINFS = new IBCQINFS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    CICACCU CICACCU = new CICACCU();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBCNJHIS IBCNJHSO = new IBCNJHIS();
    IBCNJHIS IBCNJHSN = new IBCNJHIS();
    IBRTMST IBRTMST = new IBRTMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCACMTS IBCACMTS;
    public void MP(SCCGWA SCCGWA, IBCACMTS IBCACMTS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACMTS = IBCACMTS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZACMTS return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_COMP_EXP_INT();
        B030_WRITE_NHIS();
        B040_REWRITE_IBTTMST();
        B050_MOD_OUTPUT_PROC();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACMTS.PRIM_AC_NO);
        CEP.TRC(SCCGWA, IBCACMTS.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCACMTS.CCY);
        if (IBCACMTS.PRIM_AC_NO.trim().length() == 0 
            && (IBCACMTS.NOSTR_CD.trim().length() == 0 
            || IBCACMTS.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        CEP.TRC(SCCGWA, IBCACMTS.EXP_DATE);
        if (IBCACMTS.EXP_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        } else {
            if (IBCACMTS.EXP_DATE < IBCACMTS.VAL_DATE) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TODATE_INVALID);
            }
        }
        B010_01_GET_AC_INFO();
        CEP.TRC(SCCGWA, IBCQINFS.RATE_MTH);
        CEP.TRC(SCCGWA, IBCACMTS.RATE);
        CEP.TRC(SCCGWA, IBCACMTS.CALR_STD);
        if (IBCQINFS.RATE_MTH != '0') {
            if (IBCACMTS.CALR_STD == 0) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CALR_STD_M);
            }
        }
    }
    public void B010_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFS);
        if (IBCACMTS.PRIM_AC_NO.trim().length() > 0) {
            IBCQINFS.PRIM_AC_NO = IBCACMTS.PRIM_AC_NO;
        } else {
            IBCQINFS.NOSTR_CD = IBCACMTS.NOSTR_CD;
            IBCQINFS.CCY = IBCACMTS.CCY;
        }
        IBCQINFS.SEQ_NO = IBCACMTS.SEQ_NO;
        S000_CALL_IBZQINFS();
        if (IBCQINFS.STS != 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
        }
        if (IBCACMTS.EXP_DATE < IBCQINFS.LSET_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.EXP_LESS_INTS);
        }
    }
    public void B010_02_CHECK_TXNBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, IBCPMORG);
        IBCPMORG.KEY.TYP = "PIB09";
        IBCPMORG.KEY.CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPRMR.DAT_PTR = IBCPMORG;
        S000_CALL_BPZPRMR();
        CEP.TRC(SCCGWA, IBCPMORG.DATA_TXT.CTL_TYP);
        if (IBCPMORG.DATA_TXT.CTL_TYP == '0') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINFS.POST_CTR) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else if (IBCPMORG.DATA_TXT.CTL_TYP == '1') {
            for (WS_I = 1; WS_TXNBR_FLAG != 'Y' 
                && WS_I <= 10; WS_I += 1) {
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == IBCPMORG.DATA_TXT.BR[WS_I-1]) {
                    WS_TXNBR_FLAG = 'Y';
                }
            }
            if (WS_TXNBR_FLAG != 'Y') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
            }
        } else {
        }
    }
    public void B020_COMP_EXP_INT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQINFS.RATE_MTH);
        if (IBCQINFS.RATE_MTH != '0') {
            IBS.init(SCCGWA, SCCCLDT);
            SCCCLDT.DATE1 = IBCQINFS.VAL_DATE;
            SCCCLDT.DATE2 = IBCACMTS.EXP_DATE;
            S000_CALL_SCSSCLDT();
            CEP.TRC(SCCGWA, SCCCLDT.DAYS);
            CEP.TRC(SCCGWA, IBCQINFS.CALR_STD);
            CEP.TRC(SCCGWA, IBCQINFS.CURR_BAL);
            WS_EXP_INT = IBCQINFS.CURR_BAL * IBCACMTS.RATE * SCCCLDT.DAYS / IBCQINFS.CALR_STD / 100;
        }
    }
    public void B030_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCNJHSO);
        IBCNJHSO.NOSTR_CD = IBCQINFS.NOSTR_CD;
        IBCNJHSO.PRIM_AC_NO = IBCQINFS.PRIM_AC_NO;
        IBCNJHSO.SEQ_NO = IBCQINFS.SEQ_NO;
        IBCNJHSO.CUSTNME = IBCACMTS.CUSTNME;
        IBCNJHSO.FUND_ATTR = IBCQINFS.FUND_ATTR;
        IBCNJHSO.VAL_DATE = IBCQINFS.VAL_DATE;
        IBCNJHSO.EXP_DATE = IBCQINFS.EXP_DATE;
        IBCNJHSO.RATE = IBCQINFS.RATE;
        IBCNJHSO.ADV_RATE = IBCQINFS.ADV_RATE;
        IBCNJHSO.OVD_RATE = IBCQINFS.OVD_RATE;
        IBCNJHSO.CALR_STD = IBCQINFS.CALR_STD;
        IBCNJHSO.CORR_BK_CD = IBCQINFS.CORR_BK_CD;
        IBCNJHSO.CORR_AC_NO = IBCQINFS.CORR_AC_NO;
        IBCNJHSO.CORR_DEPR_NO = IBCQINFS.CORR_DEPR_NO;
        IBS.init(SCCGWA, IBCNJHSN);
        IBCNJHSN.NOSTR_CD = IBCACMTS.NOSTR_CD;
        IBCNJHSN.PRIM_AC_NO = IBCACMTS.PRIM_AC_NO;
        IBCNJHSN.SEQ_NO = IBCACMTS.SEQ_NO;
        IBCNJHSN.CUSTNME = IBCACMTS.CUSTNME;
        IBCNJHSN.FUND_ATTR = IBCACMTS.FUND_ATTR;
        IBCNJHSN.VAL_DATE = IBCACMTS.VAL_DATE;
        IBCNJHSN.EXP_DATE = IBCACMTS.EXP_DATE;
        IBCNJHSN.RATE = IBCACMTS.RATE;
        IBCNJHSN.ADV_RATE = IBCACMTS.ADV_RATE;
        IBCNJHSN.OVD_RATE = IBCACMTS.OVD_RATE;
        IBCNJHSN.CALR_STD = IBCACMTS.CALR_STD;
        IBCNJHSN.CORR_BK_CD = IBCACMTS.CORR_BK_CD;
        IBCNJHSN.CORR_AC_NO = IBCACMTS.CORR_AC_NO;
        IBCNJHSN.CORR_DEPR_NO = IBCACMTS.CORR_DEPR_NO;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = IBCQINFS.PRIM_AC_NO;
        BPCPNHIS.INFO.AC_SEQ = IBCQINFS.SEQ_NO;
        CEP.TRC(SCCGWA, BPCPNHIS.INFO.AC);
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.CI_NO = IBCQINFS.CI_NO;
        BPCPNHIS.INFO.FMT_ID = "IBCNJHIS";
        BPCPNHIS.INFO.TX_CD = "0167112";
        BPCPNHIS.INFO.TX_TYP_CD = "P707";
        BPCPNHIS.INFO.CCY = IBCACMTS.CCY;
        if (IBCACMTS.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.FMT_ID_LEN = 466;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = IBCNJHSO;
        BPCPNHIS.INFO.NEW_DAT_PT = IBCNJHSN;
        S000_CALL_BPZPNHIS();
    }
    public void B040_REWRITE_IBTTMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMST);
        IBRTMST.KEY.AC_NO = IBCQINFS.AC_NO;
        T000_READ_IBTTMST_UPD();
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
        }
        IBRTMST.PRIM_AC_NO = IBCACMTS.PRIM_AC_NO;
        IBRTMST.SEQ_NO = IBCACMTS.SEQ_NO;
        IBRTMST.AC_NATR = IBCACMTS.AC_NATR;
        IBRTMST.EXP_DATE = IBCACMTS.EXP_DATE;
        IBRTMST.RATE = IBCACMTS.RATE;
        IBRTMST.ADV_RATE = IBCACMTS.ADV_RATE;
        IBRTMST.OVD_RATE = IBCACMTS.OVD_RATE;
        IBRTMST.CALR_STD = IBCACMTS.CALR_STD;
        IBRTMST.CORR_BK_CD = IBCACMTS.CORR_BK_CD;
        IBRTMST.CORR_AC_NO = IBCACMTS.CORR_AC_NO;
        IBRTMST.CORR_DEPR_NO = IBCACMTS.CORR_DEPR_NO;
        IBRTMST.EXP_INT = WS_EXP_INT;
        IBRTMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRTMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBRTMST.OIC_NO = IBCACMTS.OIC_NO;
        IBRTMST.RESP_CD = IBCACMTS.RESP_CD;
        IBRTMST.SUB_DP = IBCACMTS.SUB_DP;
        T000_REWRITE_IBTTMST();
    }
    public void B050_MOD_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOCINS);
        IBCOCINS.PRIM_AC_NO = IBRTMST.PRIM_AC_NO;
        IBCOCINS.NOSTR_CD = IBCACMTS.NOSTR_CD;
        IBCOCINS.CCY = IBRTMST.CCY;
        IBCOCINS.SEQ_NO = IBRTMST.SEQ_NO;
        IBCOCINS.CUSTNME = IBCQINFS.CUSTNME;
        IBCOCINS.FUND_ATTR = IBRTMST.FUND_ATTR;
        IBCOCINS.AC_NATR = IBRTMST.AC_NATR;
        IBCOCINS.VAL_DATE = IBRTMST.VAL_DATE;
        IBCOCINS.EXP_DATE = IBRTMST.EXP_DATE;
        IBCOCINS.RATE = IBRTMST.RATE;
        IBCOCINS.ADV_RATE = IBRTMST.ADV_RATE;
        IBCOCINS.OVD_RATE = IBRTMST.OVD_RATE;
        IBCOCINS.CALR_STD = "" + IBRTMST.CALR_STD;
        JIBS_tmp_int = IBCOCINS.CALR_STD.length();
        for (int i=0;i<3-JIBS_tmp_int;i++) IBCOCINS.CALR_STD = "0" + IBCOCINS.CALR_STD;
        IBCOCINS.CORR_BK_CD = IBRTMST.CORR_BK_CD;
        IBCOCINS.CORR_AC_NO = IBRTMST.CORR_AC_NO;
        IBCOCINS.CORR_DEPR_NO = IBRTMST.CORR_DEPR_NO;
        IBCOCINS.OIC_NO = IBRTMST.OIC_NO;
        IBCOCINS.RESP_CD = IBRTMST.RESP_CD;
        IBCOCINS.SUB_DP = IBRTMST.SUB_DP;
        IBCOCINS.AUTH_TLR = IBRTMST.AUTH_TLR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOCINS;
        SCCFMT.DATA_LEN = 729;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZQINFS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINFS", IBCQINFS);
        if (IBCQINFS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINFS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            CEP.ERR(SCCGWA, SCCCLDT.RC);
        }
    }
    public void T000_READ_IBTTMST_UPD() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRTMST, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_REWRITE_IBTTMST() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBS.REWRITE(SCCGWA, IBRTMST, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
