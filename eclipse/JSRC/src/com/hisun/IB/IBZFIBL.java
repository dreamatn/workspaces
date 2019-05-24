package com.hisun.IB;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class IBZFIBL {
    brParm IBTBALF_BR = new brParm();
    DBParm IBTMST_RD;
    DBParm IBTBALF_RD;
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    String K_IB_MMO = "IB";
    String TAB_IBTBALF = "IBTBALF ";
    String TAB_IBTMST = "IBTMST ";
    String PGM_SCSSCLDT = "SCSSCLDT";
    int WS_VALUE_DATE = 0;
    char WS_BALF_REC_FLG = ' ';
    SCCCLDT SCCCLDT = new SCCCLDT();
    IBCQINF IBCQINF = new IBCQINF();
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    IBRMST IBRMST = new IBRMST();
    IBRBALF IBRBALF = new IBRBALF();
    SCCGWA SCCGWA;
    IBCFIBL IBCFIBL;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    public void MP(SCCGWA SCCGWA, IBCFIBL IBCFIBL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCFIBL = IBCFIBL;
        CEP.TRC(SCCGWA);
        A00_INIT_PROC();
        if (pgmRtn) return;
        B00_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZFIBL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A00_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBCFIBL.RC.RC_MMO = K_IB_MMO;
        IBCFIBL.RC.RC_CODE = 0;
    }
    public void B00_MAIN_PROC() throws IOException,SQLException,Exception {
        B01_CHECK_INPUT();
        if (pgmRtn) return;
        B10_WRITE_BAL_HISTORY();
        if (pgmRtn) return;
    }
    public void B01_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCFIBL.INPUT_DATA.AC_NO);
        CEP.TRC(SCCGWA, IBCFIBL.INPUT_DATA.VALUE_DATE);
        CEP.TRC(SCCGWA, IBCFIBL.INPUT_DATA.SIGN);
        CEP.TRC(SCCGWA, IBCFIBL.INPUT_DATA.AMT);
        if (IBCFIBL.INPUT_DATA.AC_NO.trim().length() == 0 
            || IBCFIBL.INPUT_DATA.VALUE_DATE == 0 
            || IBCFIBL.INPUT_DATA.SIGN == ' ' 
            || IBCFIBL.INPUT_DATA.AMT == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT, IBCFIBL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCFIBL.INPUT_DATA.AC_NO;
        T00_READ_IBTMST();
        if (pgmRtn) return;
    }
    public void B10_WRITE_BAL_HISTORY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRBALF);
        IBRBALF.KEY.AC_NO = IBCFIBL.INPUT_DATA.AC_NO;
        IBRBALF.KEY.BAL_DATE = IBCFIBL.INPUT_DATA.VALUE_DATE;
        T00_READ_IBTBALF();
        if (pgmRtn) return;
        if (WS_BALF_REC_FLG == 'N') {
            IBRBALF.BAL = IBRMST.VALUE_BAL;
            IBRBALF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRBALF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRBALF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRBALF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRBALF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T00_WRITE_IBTBALF();
            if (pgmRtn) return;
        } else {
            T00_STARTBR_IBTBALF();
            if (pgmRtn) return;
            T00_READNEXT_IBTBALF();
            if (pgmRtn) return;
            if (WS_BALF_REC_FLG == 'N') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOTFND_BALF, IBCFIBL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            while (WS_BALF_REC_FLG != 'N') {
                T00_READ_IBTBALF_UPD();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
                    if (IBCFIBL.INPUT_DATA.SIGN == 'D') {
                        IBRBALF.BAL = IBRBALF.BAL + IBCFIBL.INPUT_DATA.AMT;
                    } else {
                        IBRBALF.BAL = IBRBALF.BAL - IBCFIBL.INPUT_DATA.AMT;
                    }
                } else {
                    if (IBCFIBL.INPUT_DATA.SIGN == 'D') {
                        IBRBALF.BAL = IBRBALF.BAL - IBCFIBL.INPUT_DATA.AMT;
                    } else {
                        IBRBALF.BAL = IBRBALF.BAL + IBCFIBL.INPUT_DATA.AMT;
                    }
                }
                if (IBRMST.OD_FLAG == 'N' 
                    && IBRBALF.BAL < 0) {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_OD, IBCFIBL.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                IBRBALF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBRBALF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRBALF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                T00_REWRITE_IBTBALF();
                if (pgmRtn) return;
                T00_READNEXT_IBTBALF();
                if (pgmRtn) return;
            }
        }
    }
    public void S00_CALL_SCSSCLDT() throws IOException,SQLException,Exception {
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        if (SCCCLDT.RC != 0) {
            IBCFIBL.RC.RC_CODE = SCCCLDT.RC;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T00_STARTBR_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_BR.rp = new DBParm();
        IBTBALF_BR.rp.TableName = "IBTBALF";
        IBTBALF_BR.rp.where = "AC_NO = :IBRBALF.KEY.AC_NO "
            + "AND BAL_DATE >= :IBRBALF.KEY.BAL_DATE";
        IBS.STARTBR(SCCGWA, IBRBALF, this, IBTBALF_BR);
    }
    public void T00_READNEXT_IBTBALF() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRBALF, this, IBTBALF_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BALF_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BALF_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TAB_IBTBALF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_IBTMST() throws IOException,SQLException,Exception {
        IBTMST_RD = new DBParm();
        IBTMST_RD.TableName = "IBTMST";
        IBS.READ(SCCGWA, IBRMST, IBTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCFIBL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TAB_IBTMST;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBS.READ(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BALF_REC_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BALF_REC_FLG = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TAB_IBTBALF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_READ_IBTBALF_UPD() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBTBALF_RD.upd = true;
        IBS.READ(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TAB_IBTBALF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_WRITE_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBS.WRITE(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TAB_IBTBALF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T00_REWRITE_IBTBALF() throws IOException,SQLException,Exception {
        IBTBALF_RD = new DBParm();
        IBTBALF_RD.TableName = "IBTBALF";
        IBS.REWRITE(SCCGWA, IBRBALF, IBTBALF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TAB_IBTBALF;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (IBCFIBL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "IBCFIBL = ");
            CEP.TRC(SCCGWA, IBCFIBL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
