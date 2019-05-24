package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRHISM {
    DBParm BPTIHIS_RD;
    boolean pgmRtn = false;
    String TBL_BPTIHIS = "BPTIHIS ";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_DATE = 0;
    int WS_TIME = 0;
    int WS_FW_DATE = 0;
    int WS_FW_TIME = 0;
    char WS_RATE_FLG = ' ';
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBANK BPRBANK = new BPRBANK();
    BPRIHIS BPRIHIS = new BPRIHIS();
    SCCGWA SCCGWA;
    BPCRHISM BPCRHISM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCRHISM BPCRHISM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRHISM = BPCRHISM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRHISM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRHISM.RC);
        IBS.init(SCCGWA, BPRIHIS);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAINT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPCRHISM.FUNC != 'I' 
            && BPCRHISM.FUNC != 'A' 
            && BPCRHISM.FUNC != 'D' 
            && BPCRHISM.FUNC != 'U')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR, BPCRHISM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCRHISM.DT > SCCGWA.COMM_AREA.AC_DATE) {
            WS_RATE_FLG = 'F';
            WS_DATE = 29991231;
        } else {
            WS_RATE_FLG = 'B';
            WS_DATE = BPCRHISM.DT;
        }
    }
    public void B200_MAINT_PROCESS() throws IOException,SQLException,Exception {
        if (BPCRHISM.FUNC == 'I') {
            B210_QUERY_PROC();
            if (pgmRtn) return;
        } else if (BPCRHISM.FUNC == 'A') {
            if (WS_RATE_FLG == 'F') {
                B230_DELETE_PROC();
                if (pgmRtn) return;
            }
            B220_ADD_PROC();
            if (pgmRtn) return;
        } else if (BPCRHISM.FUNC == 'D') {
            B230_DELETE_PROC();
            if (pgmRtn) return;
        } else if (BPCRHISM.FUNC == 'U') {
            B240_UPDATE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR, BPCRHISM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_QUERY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIS);
        WS_FW_DATE = 0;
        WS_FW_TIME = 0;
        BPRIHIS.KEY.BR = BPCRHISM.BR;
        BPRIHIS.KEY.CCY = BPCRHISM.CCY;
        BPRIHIS.KEY.BASE_TYP = BPCRHISM.BASE_TYP;
        BPRIHIS.KEY.TENOR = BPCRHISM.TENOR;
        BPRIHIS.KEY.DT = WS_DATE;
        BPRIHIS.KEY.TM = BPCRHISM.TM;
        if (WS_RATE_FLG == 'F') {
            T000_READ_BRTIHIS_FW();
            if (pgmRtn) return;
        } else {
            T000_READ_BPTIHIS();
            if (pgmRtn) return;
        }
    }
    public void B220_ADD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIS);
        BPRIHIS.KEY.BR = BPCRHISM.BR;
        BPRIHIS.KEY.CCY = BPCRHISM.CCY;
        BPRIHIS.KEY.BASE_TYP = BPCRHISM.BASE_TYP;
        BPRIHIS.KEY.TENOR = BPCRHISM.TENOR;
        BPRIHIS.KEY.DT = BPCRHISM.DT;
        BPRIHIS.KEY.TM = BPCRHISM.TM;
        BPRIHIS.REF_BR = BPCRHISM.REF_BR;
        BPRIHIS.REF_CCY = BPCRHISM.REF_CCY;
        BPRIHIS.REF_BASE_TYP = BPCRHISM.REF_BASE_TYP;
        BPRIHIS.REF_TENOR = BPCRHISM.REF_TENOR;
        BPRIHIS.FORMAT = BPCRHISM.FORMAT;
        BPRIHIS.DIFF = BPCRHISM.DIFF;
        BPRIHIS.RATE = BPCRHISM.RATE;
        BPRIHIS.REF_DEPTH = BPCRHISM.REF_DEPTH;
        BPRIHIS.TELLER = BPCRHISM.TELLER;
        BPRIHIS.OVR1 = BPCRHISM.OVR1;
        BPRIHIS.OVR2 = BPCRHISM.OVR2;
        CEP.TRC(SCCGWA, BPRIHIS);
        T000_WRITE_BPTIHIS();
        if (pgmRtn) return;
    }
    public void B230_DELETE_PROC() throws IOException,SQLException,Exception {
        if (WS_RATE_FLG == 'F') {
            B210_QUERY_PROC();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                if (WS_FW_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                    IBS.init(SCCGWA, BPRIHIS);
                    BPRIHIS.KEY.BR = BPCRHISM.BR;
                    BPRIHIS.KEY.CCY = BPCRHISM.CCY;
                    BPRIHIS.KEY.BASE_TYP = BPCRHISM.BASE_TYP;
                    BPRIHIS.KEY.TENOR = BPCRHISM.TENOR;
                    BPRIHIS.KEY.DT = WS_FW_DATE;
                    BPRIHIS.KEY.TM = WS_FW_TIME;
                    T000_READUP_BPTIHIS();
                    if (pgmRtn) return;
                    T000_DELETE_BPTIHIS();
                    if (pgmRtn) return;
                }
            }
        } else {
            IBS.init(SCCGWA, BPRIHIS);
            BPRIHIS.KEY.BR = BPCRHISM.BR;
            BPRIHIS.KEY.CCY = BPCRHISM.CCY;
            BPRIHIS.KEY.BASE_TYP = BPCRHISM.BASE_TYP;
            BPRIHIS.KEY.TENOR = BPCRHISM.TENOR;
            BPRIHIS.KEY.DT = BPCRHISM.DT;
            BPRIHIS.KEY.TM = BPCRHISM.TM;
            T000_READUP_BPTIHIS();
            if (pgmRtn) return;
            T000_DELETE_BPTIHIS();
            if (pgmRtn) return;
        }
    }
    public void B240_UPDATE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIS);
        if (WS_RATE_FLG == 'F') {
            B210_QUERY_PROC();
            if (pgmRtn) return;
            if (WS_FW_DATE > SCCGWA.COMM_AREA.AC_DATE) {
                BPRIHIS.KEY.DT = WS_FW_DATE;
                BPRIHIS.KEY.TM = WS_FW_TIME;
            } else {
                BPRIHIS.KEY.DT = BPCRHISM.DT;
                BPRIHIS.KEY.TM = BPCRHISM.TM;
            }
        } else {
            BPRIHIS.KEY.DT = BPCRHISM.DT;
            BPRIHIS.KEY.TM = BPCRHISM.TM;
        }
        BPRIHIS.KEY.BR = BPCRHISM.BR;
        BPRIHIS.KEY.CCY = BPCRHISM.CCY;
        BPRIHIS.KEY.BASE_TYP = BPCRHISM.BASE_TYP;
        BPRIHIS.KEY.TENOR = BPCRHISM.TENOR;
        CEP.TRC(SCCGWA, BPRIHIS.KEY.BR);
        CEP.TRC(SCCGWA, BPRIHIS.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRIHIS.KEY.TENOR);
        CEP.TRC(SCCGWA, BPRIHIS.KEY.DT);
        CEP.TRC(SCCGWA, BPRIHIS.KEY.TM);
        T000_READUP_BPTIHIS();
        if (pgmRtn) return;
        BPRIHIS.KEY.DT = BPCRHISM.DT;
        BPRIHIS.KEY.TM = BPCRHISM.TM;
        BPRIHIS.REF_BR = BPCRHISM.REF_BR;
        BPRIHIS.REF_CCY = BPCRHISM.REF_CCY;
        BPRIHIS.REF_BASE_TYP = BPCRHISM.REF_BASE_TYP;
        BPRIHIS.REF_TENOR = BPCRHISM.REF_TENOR;
        BPRIHIS.FORMAT = BPCRHISM.FORMAT;
        BPRIHIS.DIFF = BPCRHISM.DIFF;
        BPRIHIS.RATE = BPCRHISM.RATE;
        BPRIHIS.REF_DEPTH = BPCRHISM.REF_DEPTH;
        BPRIHIS.TELLER = BPCRHISM.TELLER;
        BPRIHIS.OVR1 = BPCRHISM.OVR1;
        BPRIHIS.OVR2 = BPCRHISM.OVR2;
        CEP.TRC(SCCGWA, BPRIHIS.KEY.BR);
        CEP.TRC(SCCGWA, BPRIHIS.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRIHIS.KEY.TENOR);
        CEP.TRC(SCCGWA, BPRIHIS.KEY.DT);
        CEP.TRC(SCCGWA, BPRIHIS.KEY.TM);
        T000_UPDATE_BPTIHIS();
        if (pgmRtn) return;
    }
    public void T000_WRITE_BPTIHIS() throws IOException,SQLException,Exception {
        BPTIHIS_RD = new DBParm();
        BPTIHIS_RD.TableName = "BPTIHIS";
        IBS.WRITE(SCCGWA, BPRIHIS, BPTIHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCRHISM.RC);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_KEY_DUPLICATE, BPCRHISM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTIHIS;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_UPDATE_BPTIHIS() throws IOException,SQLException,Exception {
        BPTIHIS_RD = new DBParm();
        BPTIHIS_RD.TableName = "BPTIHIS";
        IBS.REWRITE(SCCGWA, BPRIHIS, BPTIHIS_RD);
    }
    public void T000_READ_BPTIHIS() throws IOException,SQLException,Exception {
        BPTIHIS_RD = new DBParm();
        BPTIHIS_RD.TableName = "BPTIHIS";
        IBS.READ(SCCGWA, BPRIHIS, BPTIHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHISM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BRTIHIS_FW() throws IOException,SQLException,Exception {
        BPTIHIS_RD = new DBParm();
        BPTIHIS_RD.TableName = "BPTIHIS";
        BPTIHIS_RD.where = "BR = :BPRIHIS.KEY.BR "
            + "AND CCY = :BPRIHIS.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIS.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIS.KEY.TENOR "
            + "AND DT <= :BPRIHIS.KEY.DT";
        BPTIHIS_RD.fst = true;
        BPTIHIS_RD.order = "DT DESC";
        IBS.READ(SCCGWA, BPRIHIS, this, BPTIHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (BPRIHIS.KEY.DT <= SCCGWA.COMM_AREA.AC_DATE) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHISM.RC);
            } else {
                WS_FW_DATE = BPRIHIS.KEY.DT;
                WS_FW_TIME = BPRIHIS.KEY.TM;
            }
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHISM.RC);
        } else {
        }
    }
    public void T000_READUP_BPTIHIS() throws IOException,SQLException,Exception {
        null.upd = true;
        BPTIHIS_RD = new DBParm();
        BPTIHIS_RD.TableName = "BPTIHIS";
        IBS.READ(SCCGWA, BPRIHIS, BPTIHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHISM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_DELETE_BPTIHIS() throws IOException,SQLException,Exception {
        BPTIHIS_RD = new DBParm();
        BPTIHIS_RD.TableName = "BPTIHIS";
        IBS.DELETE(SCCGWA, BPRIHIS, BPTIHIS_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRHISM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRHISM = ");
            CEP.TRC(SCCGWA, BPCRHISM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
