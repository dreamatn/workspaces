package com.hisun.IB;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZFATRU {
    DBParm IBTMST_RD;
    DBParm IBTTMST_RD;
    boolean pgmRtn = false;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    String WS_TABLE_NAME = " ";
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBRTMST IBRTMST = new IBRTMST();
    IBRMST IBRMST = new IBRMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCFATRU IBCFATRU;
    public void MP(SCCGWA SCCGWA, IBCFATRU IBCFATRU) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCFATRU = IBCFATRU;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZFATRU return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBCFATRU.RC.RC_MMO = " ";
        IBCFATRU.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MODIFY_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCFATRU.AC_NO);
        if (IBCFATRU.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.IB_AC_NO_M_INPUT, IBCFATRU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCFATRU.FUND_ATTR);
        if (IBCFATRU.FUND_ATTR == ' ') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.FUND_ATTR_M, IBCFATRU.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCFATRU.AC_NO;
        CEP.TRC(SCCGWA, IBRMST.KEY.AC_NO);
        T000_READ_IBTMST_UPD();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'Y') {
            IBRMST.PROD_CD = IBCFATRU.PROD_CD;
            IBRMST.FUND_ATTR = IBCFATRU.FUND_ATTR;
            IBRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            IBRMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_IBTMST();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, IBRTMST);
            IBRTMST.PRIM_AC_NO = IBCFATRU.AC_NO;
            IBRTMST.SEQ_NO = IBCFATRU.SEQ_NO;
            T000_READ_IBTTMST_UPD();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCFATRU.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            IBRTMST.PROD_CD = IBCFATRU.PROD_CD;
            IBRTMST.FUND_ATTR = IBCFATRU.FUND_ATTR;
            IBRTMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRTMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            IBRTMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_IBTTMST();
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
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTTMST_UPD() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.where = "PRIM_AC_NO = :IBRTMST.PRIM_AC_NO "
            + "AND SEQ_NO = :IBRTMST.SEQ_NO";
        IBTTMST_RD.upd = true;
        IBS.READ(SCCGWA, IBRTMST, this, IBTTMST_RD);
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
    public void T000_REWRITE_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.REWRITE(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
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
