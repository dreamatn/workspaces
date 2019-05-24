package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRHITM {
    DBParm BPTIHIT_RD;
    boolean pgmRtn = false;
    String TBL_BPTIHIT = "BPTIHIT ";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_DATE = 0;
    int WS_FW_DATE = 0;
    char WS_RATE_FLG = ' ';
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBANK BPRBANK = new BPRBANK();
    BPRIHIT BPRIHIT = new BPRIHIT();
    SCCGWA SCCGWA;
    BPCRHITM BPCRHITM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCRHITM BPCRHITM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRHITM = BPCRHITM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRHITM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRHITM.RC);
        IBS.init(SCCGWA, BPRIHIT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAINT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPCRHITM.FUNC != 'I' 
            && BPCRHITM.FUNC != 'A' 
            && BPCRHITM.FUNC != 'D' 
            && BPCRHITM.FUNC != 'U' 
            && BPCRHITM.FUNC != 'R')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_FUNC_ERROR, BPCRHITM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCRHITM.BR);
        CEP.TRC(SCCGWA, BPCRHITM.CCY);
        CEP.TRC(SCCGWA, BPCRHITM.BASE_TYP);
        CEP.TRC(SCCGWA, BPCRHITM.TENOR);
        CEP.TRC(SCCGWA, BPCRHITM.DT);
        if (BPCRHITM.DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_RATE_FLG = 'F';
            WS_DATE = 29991231;
        } else {
            WS_RATE_FLG = 'B';
            WS_DATE = BPCRHITM.DT;
        }
    }
    public void B200_MAINT_PROCESS() throws IOException,SQLException,Exception {
        if (BPCRHITM.FUNC == 'I') {
            B210_QUERY_PROC();
            if (pgmRtn) return;
        } else if (BPCRHITM.FUNC == 'A') {
            if (WS_RATE_FLG == 'F') {
                B230_DELETE_PROC();
                if (pgmRtn) return;
            }
            B220_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHITM.FUNC == 'D') {
            B230_DELETE_PROC();
            if (pgmRtn) return;
        } else if (BPCRHITM.FUNC == 'R') {
            B240_READUP_PROC();
            if (pgmRtn) return;
        } else if (BPCRHITM.FUNC == 'U') {
            B250_UPDATE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR, BPCRHITM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_QUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        WS_FW_DATE = 0;
        BPRIHIT.KEY.BR = BPCRHITM.BR;
        BPRIHIT.KEY.CCY = BPCRHITM.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITM.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITM.TENOR;
        BPRIHIT.KEY.DT = WS_DATE;
        if (WS_RATE_FLG == 'F') {
            T000_READ_BRTIHIT_FW();
            if (pgmRtn) return;
        } else {
            T000_READ_BPTIHIT();
            if (pgmRtn) return;
        }
    }
    public void B220_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITM.BR;
        BPRIHIT.KEY.CCY = BPCRHITM.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITM.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITM.TENOR;
        BPRIHIT.KEY.DT = BPCRHITM.DT;
        BPRIHIT.TM = BPCRHITM.TM;
        BPRIHIT.REF_BR = BPCRHITM.REF_BR;
        BPRIHIT.REF_CCY = BPCRHITM.REF_CCY;
        BPRIHIT.REF_BASE_TYP = BPCRHITM.REF_BASE_TYP;
        BPRIHIT.REF_TENOR = BPCRHITM.REF_TENOR;
        BPRIHIT.FORMAT = BPCRHITM.FORMAT;
        BPRIHIT.DIFF = BPCRHITM.DIFF;
        BPRIHIT.RATE = BPCRHITM.RATE;
        BPRIHIT.REF_DEPTH = BPCRHITM.REF_DEPTH;
        BPRIHIT.TELLER = BPCRHITM.TELLER;
        BPRIHIT.UPDATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRIHIT.OVR1 = BPCRHITM.OVR1;
        BPRIHIT.OVR2 = BPCRHITM.OVR2;
        T000_WRITE_BPTIHIT();
        if (pgmRtn) return;
    }
    public void B230_DELETE_PROC() throws IOException,SQLException,Exception {
        if (WS_RATE_FLG == 'F') {
            B210_QUERY_PROC();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (WS_FW_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                    IBS.init(SCCGWA, BPRIHIT);
                    BPRIHIT.KEY.BR = BPCRHITM.BR;
                    BPRIHIT.KEY.CCY = BPCRHITM.CCY;
                    BPRIHIT.KEY.BASE_TYP = BPCRHITM.BASE_TYP;
                    BPRIHIT.KEY.TENOR = BPCRHITM.TENOR;
                    BPRIHIT.KEY.DT = WS_FW_DATE;
                    T000_READUP_BPTIHIT();
                    if (pgmRtn) return;
                    T000_DELETE_BPTIHIT();
                    if (pgmRtn) return;
                }
            }
        } else {
            IBS.init(SCCGWA, BPRIHIT);
            BPRIHIT.KEY.BR = BPCRHITM.BR;
            BPRIHIT.KEY.CCY = BPCRHITM.CCY;
            BPRIHIT.KEY.BASE_TYP = BPCRHITM.BASE_TYP;
            BPRIHIT.KEY.TENOR = BPCRHITM.TENOR;
            BPRIHIT.KEY.DT = BPCRHITM.DT;
            T000_READUP_BPTIHIT();
            if (pgmRtn) return;
            T000_DELETE_BPTIHIT();
            if (pgmRtn) return;
        }
    }
    public void B240_READUP_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        if (WS_RATE_FLG == 'F') {
            B210_QUERY_PROC();
            if (pgmRtn) return;
            if (WS_FW_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                BPRIHIT.KEY.DT = WS_FW_DATE;
            } else {
                BPRIHIT.KEY.DT = BPCRHITM.DT;
            }
        } else {
            BPRIHIT.KEY.DT = BPCRHITM.DT;
        }
        BPRIHIT.KEY.BR = BPCRHITM.BR;
        BPRIHIT.KEY.CCY = BPCRHITM.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITM.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITM.TENOR;
        T000_READUP_BPTIHIT();
        if (pgmRtn) return;
    }
    public void B250_UPDATE_PROC() throws IOException,SQLException,Exception {
        BPRIHIT.KEY.BR = BPCRHITM.BR;
        BPRIHIT.KEY.CCY = BPCRHITM.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITM.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITM.TENOR;
        BPRIHIT.KEY.DT = BPCRHITM.DT;
        BPRIHIT.TM = BPCRHITM.TM;
        BPRIHIT.REF_BR = BPCRHITM.REF_BR;
        BPRIHIT.REF_CCY = BPCRHITM.REF_CCY;
        BPRIHIT.REF_BASE_TYP = BPCRHITM.REF_BASE_TYP;
        BPRIHIT.REF_TENOR = BPCRHITM.REF_TENOR;
        BPRIHIT.FORMAT = BPCRHITM.FORMAT;
        BPRIHIT.DIFF = BPCRHITM.DIFF;
        BPRIHIT.RATE = BPCRHITM.RATE;
        BPRIHIT.REF_DEPTH = BPCRHITM.REF_DEPTH;
        BPRIHIT.TELLER = BPCRHITM.TELLER;
        BPRIHIT.UPDATE_DT = SCCGWA.COMM_AREA.AC_DATE;
        BPRIHIT.OVR1 = BPCRHITM.OVR1;
        BPRIHIT.OVR2 = BPCRHITM.OVR2;
        CEP.TRC(SCCGWA, BPRIHIT.KEY.BR);
        CEP.TRC(SCCGWA, BPRIHIT.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRIHIT.KEY.TENOR);
        CEP.TRC(SCCGWA, BPRIHIT.KEY.DT);
        T000_UPDATE_BPTIHIT();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTIHIT() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        IBS.WRITE(SCCGWA, BPRIHIT, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRHITM.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_KEY_DUPLICATE, BPCRHITM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTIHIT;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_BPTIHIT() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        IBS.REWRITE(SCCGWA, BPRIHIT, BPTIHIT_RD);
    }
    public void T000_READ_BRTIHIT_FW() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT <= :BPRIHIT.KEY.DT";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "DT DESC";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (BPRIHIT.KEY.DT <= SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITM.RC);
            } else {
                WS_FW_DATE = BPRIHIT.KEY.DT;
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITM.RC);
        } else {
        }
    }
    public void T000_READ_BPTIHIT() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        IBS.READ(SCCGWA, BPRIHIT, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READUP_BPTIHIT() throws IOException,SQLException,Exception {
        null.upd = true;
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        IBS.READ(SCCGWA, BPRIHIT, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_DELETE_BPTIHIT() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        IBS.DELETE(SCCGWA, BPRIHIT, BPTIHIT_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRHITM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRHITM = ");
            CEP.TRC(SCCGWA, BPCRHITM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
