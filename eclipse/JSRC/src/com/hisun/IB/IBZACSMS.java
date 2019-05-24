package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACSMS {
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTTMST_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_OUTPUT_FMT = "IBE52   ";
    String K_STATUS = "00000000000000000000000000000000";
    String WS_TABLE_NAME = " ";
    short WS_I = 0;
    IBZACSMS_WS_AC_STATUS WS_AC_STATUS = new IBZACSMS_WS_AC_STATUS();
    char WS_TABLE_REC = ' ';
    char WS_TXNBR_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCOCINS IBCOCINS = new IBCOCINS();
    IBCQINFS IBCQINFS = new IBCQINFS();
    IBCPMORG IBCPMORG = new IBCPMORG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    IBCNJHIS IBCNJHSO = new IBCNJHIS();
    IBCNJHIS IBCNJHSN = new IBCNJHIS();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    IBRTMST IBRTMST = new IBRTMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCACSMS IBCACSMS;
    public void MP(SCCGWA SCCGWA, IBCACSMS IBCACSMS) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACSMS = IBCACSMS;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZACSMS return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B011_GET_AC_INFO_UPD();
        if (pgmRtn) return;
        B012_VALIDATE_INPUT_STS();
        if (pgmRtn) return;
        B013_UPDATE_STS_WORD();
        if (pgmRtn) return;
        B014_REWRITE_PROC();
        if (pgmRtn) return;
        B015_STS_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACSMS.PRIM_AC_NO);
        if (IBCACSMS.PRIM_AC_NO.trim().length() == 0 
            && (IBCACSMS.NOSTR_CD.trim().length() == 0 
            || IBCACSMS.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        CEP.TRC(SCCGWA, IBCACSMS.STS);
        if (IBCACSMS.STS.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.STS_MUST_INPUT);
        }
    }
    public void B011_GET_AC_INFO_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINFS);
        if (IBCACSMS.PRIM_AC_NO.trim().length() > 0) {
            IBCQINFS.PRIM_AC_NO = IBCACSMS.PRIM_AC_NO;
        } else {
            IBCQINFS.NOSTR_CD = IBCACSMS.NOSTR_CD;
            IBCQINFS.CCY = IBCACSMS.CCY;
        }
        IBCQINFS.SEQ_NO = IBCACSMS.SEQ_NO;
        S000_CALL_IBZQINFS();
        if (pgmRtn) return;
    }
    public void B011_01_CHECK_TXNBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, IBCPMORG);
        IBCPMORG.KEY.TYP = "PIB09";
        IBCPMORG.KEY.CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPRMR.DAT_PTR = IBCPMORG;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
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
    public void B012_VALIDATE_INPUT_STS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQINFS.STS);
        if (IBCQINFS.STS == 'C') {
            if (!IBCACSMS.STS.equalsIgnoreCase(IBCQINFS.STS+"")) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.CHANGE_ERROR);
            }
        }
    }
    public void B013_UPDATE_STS_WORD() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, K_STATUS, WS_AC_STATUS);
        if (IBCACSMS.STS.equalsIgnoreCase("N")) {
            WS_AC_STATUS.WS_STS_NORMA = '1';
        } else {
            if (IBCACSMS.STS.equalsIgnoreCase("C")) {
                WS_AC_STATUS.WS_STS_CLOSD = '1';
            } else {
                if (IBCACSMS.STS.equalsIgnoreCase("B")) {
                    WS_AC_STATUS.WS_STS_BLOCK = '1';
                } else {
                    if (IBCACSMS.STS.equalsIgnoreCase("L")) {
                        WS_AC_STATUS.WS_STS_LHOLD = '1';
                    }
                }
            }
        }
        CEP.TRC(SCCGWA, WS_AC_STATUS);
        B013_01_WRITE_NHIS();
        if (pgmRtn) return;
    }
    public void B013_01_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCNJHSO);
        IBCNJHSO.NOSTR_CD = IBCQINFS.NOSTR_CD;
        IBCNJHSO.CCY = IBCQINFS.CCY;
        IBCNJHSO.AC_NO = IBCQINFS.AC_NO;
        IBCNJHSO.STATUS = IBCQINFS.STS;
        IBS.init(SCCGWA, IBCNJHSN);
        IBCNJHSN.NOSTR_CD = IBCACSMS.NOSTR_CD;
        IBCNJHSN.CCY = IBCACSMS.CCY;
        IBCNJHSN.AC_NO = IBCACSMS.AC_NO;
        IBCNJHSN.STATUS = IBCACSMS.STS.charAt(0);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = IBCQINFS.PRIM_AC_NO;
        BPCPNHIS.INFO.AC_SEQ = IBCQINFS.SEQ_NO;
        BPCPNHIS.INFO.CI_NO = IBCQINFS.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.TX_CD = "0167152";
        BPCPNHIS.INFO.TX_TYP_CD = "P708";
        BPCPNHIS.INFO.CCY = IBCACSMS.CCY;
        if (IBCACSMS.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.FMT_ID_LEN = 466;
        BPCPNHIS.INFO.FMT_ID = "IBCNJHIT";
        BPCPNHIS.INFO.FMT_ID_LEN = 466;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = IBCNJHSO;
        BPCPNHIS.INFO.NEW_DAT_PT = IBCNJHSN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B014_REWRITE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMST);
        IBRTMST.KEY.AC_NO = IBCQINFS.AC_NO;
        T000_READ_IBTTMST_UPD();
        if (pgmRtn) return;
        IBRTMST.STS = IBCACSMS.STS.charAt(0);
        IBRTMST.STSW = IBS.CLS2CPY(SCCGWA, WS_AC_STATUS);
        IBRTMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRTMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRTMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_IBTTMST();
        if (pgmRtn) return;
    }
    public void B015_STS_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOCINS);
        IBCOCINS.NOSTR_CD = IBCQINFS.NOSTR_CD;
        IBCOCINS.CCY = IBRTMST.CCY;
        IBCOCINS.PRIM_AC_NO = IBRTMAIN.KEY.AC_NO;
        IBCOCINS.SEQ_NO = IBCACSMS.SEQ_NO;
        IBCOCINS.CUSTNME = IBCQINFS.CUSTNME;
        IBCOCINS.VAL_DATE = IBRTMST.VAL_DATE;
        IBCOCINS.EXP_DATE = IBRTMST.EXP_DATE;
        IBCOCINS.STS = IBRTMST.STS;
        IBCOCINS.AUTH_TLR = IBRTMST.AUTH_TLR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOCINS;
        SCCFMT.DATA_LEN = 729;
        IBS.FMT(SCCGWA, SCCFMT);
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
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_IBTTMST_UPD() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRTMST, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCACSMS.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
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
