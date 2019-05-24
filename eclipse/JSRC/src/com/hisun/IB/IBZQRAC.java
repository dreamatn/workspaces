package com.hisun.IB;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZQRAC {
    DBParm IBTRLAAC_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    String WS_TABLE_NAME = " ";
    char WS_TABLE_REC = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBRRLAAC IBRRLAAC = new IBRRLAAC();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCQRAC IBCQRAC;
    public void MP(SCCGWA SCCGWA, IBCQRAC IBCQRAC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCQRAC = IBCQRAC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZQRAC return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBCQRAC.RC.RC_MMO = " ";
        IBCQRAC.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_INQUIRE_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCQRAC.BANK_ID);
        CEP.TRC(SCCGWA, IBCQRAC.AC_TYP);
        if (IBCQRAC.BANK_ID.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CORP_ID_M, IBCQRAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (IBCQRAC.AC_TYP.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_TYP_M, IBCQRAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_INQUIRE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRRLAAC);
        IBRRLAAC.KEY.CORP_ID = IBCQRAC.BANK_ID;
        IBRRLAAC.KEY.AC_TYP = IBCQRAC.AC_TYP;
        T000_READ_IBTRLAAC();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.REC_NOTFND, IBCQRAC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBCQRAC.AC_NO = IBRRLAAC.AC_NO;
    }
    public void T000_READ_IBTRLAAC() throws IOException,SQLException,Exception {
        IBTRLAAC_RD = new DBParm();
        IBTRLAAC_RD.TableName = "IBTRLAAC";
        IBS.READ(SCCGWA, IBRRLAAC, IBTRLAAC_RD);
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
