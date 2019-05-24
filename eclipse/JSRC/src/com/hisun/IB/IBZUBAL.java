package com.hisun.IB;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZUBAL {
    brParm IBTBALF_BR = new brParm();
    DBParm IBTBALF_RD;
    DBParm IBTMST_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    String K_IB_MMO = "IB";
    int WS_VALUE_DATE = 0;
    char WS_BALF_REC_FLG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBRMST IBRMST = new IBRMST();
    IBRTMST IBRTMST = new IBRTMST();
    IBRBALF IBRBALF = new IBRBALF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCUBAL IBCUBAL;
    public void MP(SCCGWA SCCGWA, IBCUBAL IBCUBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCUBAL = IBCUBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZUBAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBCUBAL.RC.RC_MMO = K_IB_MMO;
        IBCUBAL.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_WRITE_BAL_HIST();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCUBAL.AC_NO);
        CEP.TRC(SCCGWA, IBCUBAL.VALUE_DATE);
        CEP.TRC(SCCGWA, IBCUBAL.SIGN);
        CEP.TRC(SCCGWA, IBCUBAL.AMT);
        if (IBCUBAL.AC_NO.trim().length() == 0 
            || IBCUBAL.VALUE_DATE == 0 
            || IBCUBAL.SIGN == ' ' 
            || IBCUBAL.AMT == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT, IBCUBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCUBAL.FUNC);
        if (IBCUBAL.FUNC != '1' 
            && IBCUBAL.FUNC != ' ') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.INPUT, IBCUBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_WRITE_BAL_HIST() throws IOException,SQLException,Exception {
        if (IBCUBAL.FUNC == '1' 
            || IBCUBAL.FUNC == ' ') {
            IBS.init(SCCGWA, IBRMST);
            IBRMST.KEY.AC_NO = IBCUBAL.AC_NO;
            T000_READ_IBTMST();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, IBRBALF);
        IBRBALF.KEY.AC_NO = IBCUBAL.AC_NO;
        IBRBALF.KEY.BAL_DATE = IBCUBAL.VALUE_DATE;
        T000_READ_IBTBALF();
        if (pgmRtn) return;
        if (WS_BALF_REC_FLG == 'N') {
            T000_READ_IBTBALF_FIRST();
            if (pgmRtn) return;
            if (WS_BALF_REC_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_BALF, IBCUBAL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            B020_01_CAL_BAL();
            if (pgmRtn) return;
            IBRBALF.KEY.AC_NO = IBCUBAL.AC_NO;
            IBRBALF.KEY.BAL_DATE = IBCUBAL.VALUE_DATE;
            IBRBALF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRBALF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRBALF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRBALF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRBALF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_WRITE_IBTBALF();
            if (pgmRtn) return;
        } else {
            T000_READ_IBTBALF_UPD();
            if (pgmRtn) return;
            B020_01_CAL_BAL();
            if (pgmRtn) return;
            IBRBALF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRBALF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRBALF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_REWRITE_IBTBALF();
            if (pgmRtn) return;
        }
        T000_STARTBR_IBTBALF();
        if (pgmRtn) return;
        while (WS_BALF_REC_FLG != 'N') {
            T000_READNEXT_IBTBALF();
            if (pgmRtn) return;
            if (WS_BALF_REC_FLG == 'Y') {
                T000_READ_IBTBALF_UPD();
                if (pgmRtn) return;
                B020_01_CAL_BAL();
                if (pgmRtn) return;
                IBRBALF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBRBALF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRBALF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                T000_REWRITE_IBTBALF();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_01_CAL_BAL() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            if (IBCUBAL.SIGN == 'D') {
                IBRBALF.BAL = IBRBALF.BAL + IBCUBAL.AMT;
            } else {
                IBRBALF.BAL = IBRBALF.BAL - IBCUBAL.AMT;
            }
        } else {
            if (IBCUBAL.SIGN == 'D') {
                IBRBALF.BAL = IBRBALF.BAL - IBCUBAL.AMT;
            } else {
                IBRBALF.BAL = IBRBALF.BAL + IBCUBAL.AMT;
            }
        }
        if (IBRBALF.BAL < 0 
            && (IBRMST.OD_FLAG != 'Y' 
            || IBRMST.HLD_AMT != 0)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_OD, IBCUBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_BR.rp = new DBParm();
        IBTBALF_BR.rp.TableName = "IBTBALF";
        IBTBALF_BR.rp.where = "AC_NO = :IBRBALF.KEY.AC_NO "
            + "AND BAL_DATE > :IBRBALF.KEY.BAL_DATE";
        IBS.STARTBR(SCCGWA, IBRBALF, this, IBTBALF_BR);
    }
    public void T000_READ_IBTBALF_FIRST() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBTBALF_RD.where = "AC_NO = :IBRBALF.KEY.AC_NO "
            + "AND BAL_DATE < :IBRBALF.KEY.BAL_DATE";
        IBTBALF_RD.fst = true;
        IBTBALF_RD.order = "BAL_DATE DESC";
        IBS.READ(SCCGWA, IBRBALF, this, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            WS_BALF_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            WS_BALF_REC_FLG = 'N';
        } else {
            CEP.TRC(SCCGWA, "333");
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTBALF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_IBTBALF() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRBALF, this, IBTBALF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "111");
            WS_BALF_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "222");
            WS_BALF_REC_FLG = 'N';
        } else {
            CEP.TRC(SCCGWA, "333");
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTBALF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCUBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBS.READ(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BALF_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BALF_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTBALF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTBALF_UPD() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBTBALF_RD.upd = true;
        IBS.READ(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTBALF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_WRITE_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBS.WRITE(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTBALF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBS.REWRITE(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "IBTBALF";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (IBCUBAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "IBCUBAL = ");
            CEP.TRC(SCCGWA, IBCUBAL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
