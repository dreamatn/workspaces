package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.FS.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZSHLD {
    DBParm IBTMST_RD;
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCALL SCCCALL = new SCCCALL();
    String WS_TABLE_NAME = " ";
    double WS_AVL_BAL = 0;
    String WS_OUT_FMT = " ";
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBRMST IBRMST = new IBRMST();
    IBCOSHLD IBCOSHLD = new IBCOSHLD();
    SCCFMT SCCFMT = new SCCFMT();
    FSRVMST FSRVMST = new FSRVMST();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCSHLD IBCSHLD;
    public void MP(SCCGWA SCCGWA, IBCSHLD IBCSHLD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCSHLD = IBCSHLD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "IBZSHLD return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        B020_MODIFY_PROC();
        B030_MODIFY_OUT();
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCSHLD.AC_NO);
        if (IBCSHLD.AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IB_AC_NO_M_INPUT);
        }
        if (IBCSHLD.FUNC != 'H' 
            && IBCSHLD.FUNC != 'R') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.FCN_MUST_INPUT);
        }
        if (IBCSHLD.HLDF_AMT <= 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AMT);
        }
    }
    public void B020_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCSHLD.AC_NO;
        CEP.TRC(SCCGWA, IBRMST.KEY.AC_NO);
        T000_READ_IBTMST_UPD();
        if (WS_TABLE_REC == 'Y') {
            if (IBRMST.AC_STS != 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL);
            }
            if (IBCSHLD.FUNC == 'H') {
                WS_AVL_BAL = IBRMST.VALUE_BAL - IBRMST.HLD_AMT;
                if (IBCSHLD.HLDF_AMT > WS_AVL_BAL) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_OD);
                }
                IBRMST.HLD_AMT = IBRMST.HLD_AMT + IBCSHLD.HLDF_AMT;
                WS_OUT_FMT = "IBA52";
            } else {
                if (IBCSHLD.HLDF_AMT > IBRMST.HLD_AMT) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.IB_AMT_INVALID);
                }
                IBRMST.HLD_AMT = IBRMST.HLD_AMT - IBCSHLD.HLDF_AMT;
                WS_OUT_FMT = "IBA53";
            }
            IBRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            IBRMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_IBTMST();
        } else {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
        }
    }
    public void B030_MODIFY_OUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOSHLD);
        IBCOSHLD.AC_NO = IBCSHLD.AC_NO;
        IBCOSHLD.CUSTNME = IBCSHLD.CUSTNME;
        IBCOSHLD.CCY = IBCSHLD.CCY;
        IBCOSHLD.NOSTRO_CODE = IBCSHLD.NOSTRO_CODE;
        IBCOSHLD.AC_BAL = IBCSHLD.AC_BAL;
        IBCOSHLD.HLDH_AMT = IBCSHLD.HLDH_AMT;
        IBCOSHLD.HLDF_AMT = IBCSHLD.HLDF_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = WS_OUT_FMT;
        SCCFMT.DATA_PTR = IBCOSHLD;
        SCCFMT.DATA_LEN = 357;
        IBS.FMT(SCCGWA, SCCFMT);
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
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
