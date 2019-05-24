package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZACSMT {
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTMST_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_STATUS = "00000000000000000000000000000000";
    String K_OUTPUT_FMT = "IBA51";
    String K_IBTMST = "IBTMST";
    String WS_TABLE_NAME = " ";
    IBZACSMT_WS_AWA_STATUS WS_AWA_STATUS = new IBZACSMT_WS_AWA_STATUS();
    String WS_AC_STATUS_CHG = " ";
    IBZACSMT_WS_AC_STATUS WS_AC_STATUS = new IBZACSMT_WS_AC_STATUS();
    short WS_I = 0;
    char WS_TABLE_REC = ' ';
    char WS_TXNBR_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBCOACSI IBCOACSI = new IBCOACSI();
    IBCQINF IBCQINF = new IBCQINF();
    IBCPMORG IBCPMORG = new IBCPMORG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    IBCNFHIS IBCNFHIO = new IBCNFHIS();
    IBCNFHIS IBCNFHIN = new IBCNFHIS();
    IBRMST IBRMST = new IBRMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    IBCACSMT IBCACSMT;
    public void MP(SCCGWA SCCGWA, IBCACSMT IBCACSMT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACSMT = IBCACSMT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZACSMT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
        IBCACSMT.RC.RC_MMO = " ";
        IBCACSMT.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_GET_AC_INFO_UPD();
        if (pgmRtn) return;
        B030_CHECK_INPUT_STS();
        if (pgmRtn) return;
        B040_UPDATE_TMST_STS();
        if (pgmRtn) return;
        B050_REWRITE_TMST();
        if (pgmRtn) return;
        B060_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACSMT.NOS_CD);
        CEP.TRC(SCCGWA, IBCACSMT.CCY);
        CEP.TRC(SCCGWA, IBCACSMT.AC_NO);
        CEP.TRC(SCCGWA, IBCACSMT.STS);
        if ((IBCACSMT.NOS_CD.trim().length() == 0 
            || IBCACSMT.CCY.trim().length() == 0) 
            && IBCACSMT.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBCACSMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCACSMT.STS == ' ') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.STS_MUST_INPUT, IBCACSMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_AC_INFO_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        if (IBCACSMT.AC_NO.trim().length() > 0) {
            IBCQINF.INPUT_DATA.AC_NO = IBCACSMT.AC_NO;
        } else {
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBCACSMT.NOS_CD;
            IBCQINF.INPUT_DATA.CCY = IBCACSMT.CCY;
        }
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        if (IBCQINF.OUTPUT_DATA.EFF_DATE > SCCGWA.COMM_AREA.AC_DATE) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_UNEFF);
        }
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCACSMT.AC_NO;
        CEP.TRC(SCCGWA, IBRMST.KEY.AC_NO);
        T000_READ_IBTMST_UPD();
        if (pgmRtn) return;
    }
    public void B020_01_CHECK_TXNBR() throws IOException,SQLException,Exception {
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
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR, IBCACSMT.RC);
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
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR, IBCACSMT.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
        }
    }
    public void B030_CHECK_INPUT_STS() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, IBRMST.AC_STS_WORD, WS_AC_STATUS);
        R000_FORMAT_AC_STS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_AWA_STATUS.WS_AWA_NORMA);
        CEP.TRC(SCCGWA, WS_AC_STATUS.WS_STS_NORMA);
        CEP.TRC(SCCGWA, WS_AWA_STATUS.WS_AWA_BLOCK);
        CEP.TRC(SCCGWA, WS_AC_STATUS.WS_STS_BLOCK);
        if (WS_AWA_STATUS.WS_AWA_NORMA == WS_AC_STATUS.WS_STS_NORMA 
            && WS_AWA_STATUS.WS_AWA_NORMA == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.STS_NOEXIST, IBCACSMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_AWA_STATUS.WS_AWA_BLOCK == WS_AC_STATUS.WS_STS_BLOCK 
            && WS_AWA_STATUS.WS_AWA_BLOCK == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_BLOCK, IBCACSMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_AWA_STATUS.WS_AWA_LHOLD == WS_AC_STATUS.WS_STS_LHOLD 
            && WS_AWA_STATUS.WS_AWA_LHOLD == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.HOLD, IBCACSMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if ((WS_AWA_STATUS.WS_AWA_CLOSD != WS_AC_STATUS.WS_STS_CLOSD) 
            && WS_AC_STATUS.WS_STS_CLOSD == '0') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CLOSE_ERROR, IBCACSMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_AC_STATUS.WS_STS_CLOSD == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CHANGE_ERROR, IBCACSMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B040_UPDATE_TMST_STS() throws IOException,SQLException,Exception {
        if (IBCACSMT.STS == 'N') {
            IBS.CPY2CLS(SCCGWA, K_STATUS, WS_AC_STATUS);
            WS_AC_STATUS.WS_STS_NORMA = '1';
        }
        if (IBCACSMT.STS == 'B') {
            IBS.CPY2CLS(SCCGWA, K_STATUS, WS_AC_STATUS);
            WS_AC_STATUS.WS_STS_BLOCK = '1';
        }
        if (IBCACSMT.STS == 'L') {
            IBS.CPY2CLS(SCCGWA, K_STATUS, WS_AC_STATUS);
            WS_AC_STATUS.WS_STS_LHOLD = '1';
        }
        WS_AC_STATUS_CHG = IBRMST.AC_STS_WORD;
        IBRMST.AC_STS_WORD = IBS.CLS2CPY(SCCGWA, WS_AC_STATUS);
        IBRMST.AC_STS = IBCACSMT.STS;
        IBRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        B040_01_WRITE_NHIS();
        if (pgmRtn) return;
    }
    public void B040_01_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCNFHIO);
        IBCNFHIO.NOSTRO_CD = IBRMST.NOSTRO_CODE;
        IBCNFHIO.CCY = IBRMST.CCY;
        IBCNFHIO.AC_NO = IBRMST.KEY.AC_NO;
        IBCNFHIO.STATUS = WS_AC_STATUS_CHG;
        IBS.init(SCCGWA, IBCNFHIN);
        IBCNFHIN.NOSTRO_CD = IBCACSMT.NOS_CD;
        IBCNFHIN.CCY = IBCACSMT.CCY;
        IBCNFHIN.AC_NO = IBCACSMT.AC_NO;
        IBCNFHIN.STATUS = IBRMST.AC_STS_WORD;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = IBCACSMT.AC_NO;
        BPCPNHIS.INFO.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.CCY = IBCACSMT.CCY;
        if (IBCACSMT.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.TX_TYP_CD = "P702";
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "IBCNFHIS";
        BPCPNHIS.INFO.FMT_ID_LEN = 468;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = IBCNFHIO;
        BPCPNHIS.INFO.NEW_DAT_PT = IBCNFHIN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B050_REWRITE_TMST() throws IOException,SQLException,Exception {
        IBRMST.KEY.AC_NO = IBCACSMT.AC_NO;
        T000_REWRITE_IBTMST();
        if (pgmRtn) return;
    }
    public void B060_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOACSI);
        IBCOACSI.AC_NO = IBRMST.KEY.AC_NO;
        IBCOACSI.NOS_CD = IBRMST.NOSTRO_CODE;
        IBCOACSI.CCY = IBRMST.CCY;
        IBCOACSI.CUSTNME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        IBCOACSI.AC_STS = IBCQINF.OUTPUT_DATA.AC_STS;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOACSI;
        SCCFMT.DATA_LEN = 311;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_FORMAT_AC_STS() throws IOException,SQLException,Exception {
        IBS.CPY2CLS(SCCGWA, "0000", WS_AWA_STATUS);
        if (IBCACSMT.STS == 'N') {
            WS_AWA_STATUS.WS_AWA_NORMA = '1';
        } else {
            if (IBCACSMT.STS == 'B') {
                WS_AWA_STATUS.WS_AWA_BLOCK = '1';
            } else {
                if (IBCACSMT.STS == 'L') {
                    WS_AWA_STATUS.WS_AWA_LHOLD = '1';
                } else {
                    if (IBCACSMT.STS == 'C') {
                        WS_AWA_STATUS.WS_AWA_CLOSD = '1';
                    } else {
                        IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.STS_NOEXIST, IBCACSMT.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                }
            }
        }
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACSMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACSMT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACSMT.RC);
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
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCACSMT.RC);
            Z_RET();
            if (pgmRtn) return;
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
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
