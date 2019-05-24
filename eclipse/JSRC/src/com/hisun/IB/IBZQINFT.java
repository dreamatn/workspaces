package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.CI.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZQINFT {
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTTMAIN_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    String WS_TABLE_NAME = "IBTTMAIN";
    String K_MMO_IB = "IB";
    char K_AC_STATUS_CODE = '1';
    char K_AC_NORMAL = 'N';
    char K_AC_CLOSED = 'C';
    char K_AC_BLOCK = 'B';
    char K_AC_LHOLD = 'L';
    String WS_ERR_MSG = " ";
    short WS_CURS_POS = 0;
    IBZQINFT_WS_AC_STATUS WS_AC_STATUS = new IBZQINFT_WS_AC_STATUS();
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    CICACCU CICACCU = new CICACCU();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCQINFT IBCQINFT;
    public void MP(SCCGWA SCCGWA, IBCQINFT IBCQINFT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCQINFT = IBCQINFT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZQINFT return!");
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
        B020_GET_AC_INFO();
        if (pgmRtn) return;
        B030_GET_CUST_INFO();
        if (pgmRtn) return;
        B040_PROC_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQINFT.AC_NO);
        CEP.TRC(SCCGWA, IBCQINFT.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCQINFT.CCY);
        if ((IBCQINFT.AC_NO.trim().length() == 0) 
            && (IBCQINFT.NOSTR_CD.trim().length() == 0 
            || IBCQINFT.CCY.trim().length() == 0)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBCQINFT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_AC_INFO() throws IOException,SQLException,Exception {
        if (IBCQINFT.AC_NO.trim().length() > 0) {
            IBRTMAIN.KEY.AC_NO = IBCQINFT.AC_NO;
            T000_READ_IBTMAIN1();
            if (pgmRtn) return;
        } else {
            IBRTMAIN.NOSTRO_CODE = IBCQINFT.NOSTR_CD;
            IBRTMAIN.CCY = IBCQINFT.CCY;
            T000_READ_IBTMAIN2();
            if (pgmRtn) return;
        }
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_TMAIN, IBCQINFT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_CUST_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = IBRTMAIN.KEY.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
    }
    public void B040_PROC_OUTPUT() throws IOException,SQLException,Exception {
        IBCQINFT.CI_NO = CICACCU.DATA.CI_NO;
        IBCQINFT.CUSTNME = CICACCU.DATA.AC_CNM;
        IBCQINFT.NOSTR_CD = IBRTMAIN.NOSTRO_CODE;
        IBCQINFT.CCY = IBRTMAIN.CCY;
        IBCQINFT.AC_NO = IBRTMAIN.KEY.AC_NO;
        IBCQINFT.OPEN_DATE = IBRTMAIN.OPEN_DATE;
        IBCQINFT.CLOS_DATE = IBRTMAIN.CLOS_DATE;
        IBCQINFT.OPEN_BK = IBRTMAIN.OWNER_BK;
        IBCQINFT.CLOS_BR = IBRTMAIN.CLOS_BR;
        IBCQINFT.BR = IBRTMAIN.OPEN_BR;
        IBCQINFT.OPEN_BR = IBRTMAIN.OPEN_BR;
        IBCQINFT.UPD_DATE = IBRTMAIN.UPD_DATE;
        IBCQINFT.UPD_TIME = IBRTMAIN.UPD_TIME;
        IBCQINFT.UPD_TLR = IBRTMAIN.UPD_TLR;
        IBCQINFT.AC_STS = IBRTMAIN.AC_STS;
        IBCQINFT.AUTH_TLR = IBRTMAIN.AUTH_TLR;
    }
    public void S000_CALL_CIZACCU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACCU", CICACCU);
        if (CICACCU.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICACCU.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCQINFT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMAIN1() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBS.READ(SCCGWA, IBRTMAIN, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMAIN2() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBTTMAIN_RD.where = "NOSTRO_CODE = :IBRTMAIN.NOSTRO_CODE "
            + "AND CCY = :IBRTMAIN.CCY";
        IBS.READ(SCCGWA, IBRTMAIN, this, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
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
