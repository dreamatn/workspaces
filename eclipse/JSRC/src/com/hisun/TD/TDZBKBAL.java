package com.hisun.TD;

import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

import java.io.IOException;
import java.sql.SQLException;

public class TDZBKBAL {
    brParm TDTSMST_BR = new brParm();
    String K_AP_MMO = "TD";
    String WS_ERR_MSG = " ";
    char WS_CMST_REC_FLG = ' ';
    short WS_I = 0;
    TDZBKBAL_CP_PROD_CD CP_PROD_CD = new TDZBKBAL_CP_PROD_CD();
    char WS_TDTSMST_FLG = ' ';
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCHMPW SCCHMPW = new SCCHMPW();
    SCCFMT SCCFMT = new SCCFMT();
    TDRSMST TDRSMST = new TDRSMST();
    SCCGWA SCCGWA;
    TDCBKBAL TDCBKBAL;
    public void MP(SCCGWA SCCGWA, TDCBKBAL TDCBKBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.TDCBKBAL = TDCBKBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "TDZBKBAL return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHK_INPUT_PROC();
        B200_GET_TDINF_PROC();
    }
    public void B100_CHK_INPUT_PROC() throws IOException,SQLException,Exception {
        if (TDCBKBAL.OPP_AC_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, TDCMSG_ERROR_MSG.TD_Z_AC_NO_INP_ERR);
        }
    }
    public void B200_GET_TDINF_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.OPEN_DR_AC = TDCBKBAL.OPP_AC_NO;
        TDRSMST.KEY.ACO_AC = TDCBKBAL.SMK;
        T000_STARTBR_TDTSMST();
        T000_READNEXT_TDTSMST();
        WS_I = 1;
        while (WS_TDTSMST_FLG != 'N' 
            && WS_I <= 20) {
            TDCBKBAL.ITEM[WS_I-1].AC_NO = TDRSMST.AC_NO;
            TDCBKBAL.ITEM[WS_I-1].ACO_AC = TDRSMST.KEY.ACO_AC;
            TDCBKBAL.ITEM[WS_I-1].BAL = TDRSMST.BAL;
            WS_I += 1;
            T000_READNEXT_TDTSMST();
        }
        T000_ENDBR_TDTSMST();
        if (WS_TDTSMST_FLG == 'N') {
            CEP.TRC(SCCGWA, "1");
            TDCBKBAL.END_FLG = 'Y';
        } else {
            if (WS_I > 20) {
                CEP.TRC(SCCGWA, "2");
                TDCBKBAL.SMK = TDCBKBAL.ITEM[20-1].ACO_AC;
            } else {
                CEP.TRC(SCCGWA, WS_I);
                CEP.TRC(SCCGWA, WS_TDTSMST_FLG);
                CEP.ERR(SCCGWA, "1024");
            }
        }
    }
    public void T000_STARTBR_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_BR.rp = new DBParm();
        TDTSMST_BR.rp.TableName = "TDTSMST";
        TDTSMST_BR.rp.where = "OPEN_DR_AC = :TDRSMST.OPEN_DR_AC "
            + "AND ACO_AC > :TDRSMST.KEY.ACO_AC "
            + "AND ACO_STS = '0'";
        IBS.STARTBR(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTSMST_FLG = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TDTSMST_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "TDTSMST";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
        }
    }
    public void T000_READNEXT_TDTSMST() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, TDRSMST, this, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TDTSMST_FLG = 'F';
        } else {
            WS_TDTSMST_FLG = 'N';
        }
    }
    public void T000_ENDBR_TDTSMST() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, TDTSMST_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
