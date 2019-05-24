package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRINTM {
    DBParm BPTINTR_RD;
    boolean pgmRtn = false;
    String TBL_BPTINTR = "BPTINTR ";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_NEW_DT = 0;
    int WS_DATE = 0;
    char WS_RESULT_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBANK BPRBANK = new BPRBANK();
    BPRINTR BPRINTR = new BPRINTR();
    BPRINTR BPRINTRT = new BPRINTR();
    SCCGWA SCCGWA;
    BPCRINTM BPCRINTM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCRINTM BPCRINTM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRINTM = BPCRINTM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRINTM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRINTM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCRINTM.FUNC);
        CEP.TRC(SCCGWA, BPCRINTM);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAINT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPCRINTM.FUNC != 'I' 
            && BPCRINTM.FUNC != 'A' 
            && BPCRINTM.FUNC != 'D' 
            && BPCRINTM.FUNC != 'U' 
            && BPCRINTM.FUNC != 'R')) {
            CEP.TRC(SCCGWA, "CHECK-FUNC-ERROR---------");
            CEP.TRC(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_FUNC_ERROR);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_FUNC_ERROR, BPCRINTM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAINT_PROCESS() throws IOException,SQLException,Exception {
        if (BPCRINTM.FUNC == 'I') {
            B210_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            C100_OUTPUT_RINTM();
            if (pgmRtn) return;
        } else if (BPCRINTM.FUNC == 'A') {
            B210_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_RESULT_FLAG);
            CEP.TRC(SCCGWA, BPCRINTM.DT);
            CEP.TRC(SCCGWA, BPRINTR.KEY.DT);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            if (WS_RESULT_FLAG == 'N') {
                if (BPCRINTM.DT > SCCGWA.COMM_AREA.AC_DATE 
                    && BPRINTR.KEY.DT <= SCCGWA.COMM_AREA.AC_DATE) {
                    B220_ADD_RECORD_PROC();
                    if (pgmRtn) return;
                } else {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_RECORD_EXIST, BPCRINTM.RC);
                    pgmRtn = true;
                    return;
                }
            } else {
                B220_ADD_RECORD_PROC();
                if (pgmRtn) return;
            }
        } else if (BPCRINTM.FUNC == 'D') {
            B210_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            if (WS_RESULT_FLAG == 'N' 
                && (BPRINTR.KEY.DT > SCCGWA.COMM_AREA.AC_DATE 
                || BPCRINTM.DT <= SCCGWA.COMM_AREA.AC_DATE)) {
                B230_DELETE_RECORD_PROC();
                if (pgmRtn) return;
            }
        } else if (BPCRINTM.FUNC == 'R') {
            B210_QUERY_RECORD_PROC();
            if (pgmRtn) return;
            if (WS_RESULT_FLAG == 'N' 
                && (BPRINTR.KEY.DT > SCCGWA.COMM_AREA.AC_DATE 
                || BPCRINTM.DT <= SCCGWA.COMM_AREA.AC_DATE)) {
                B240_READUP_RECORD_PROC();
                if (pgmRtn) return;
            } else {
                if (BPCRINTM.DT > SCCGWA.COMM_AREA.AC_DATE) {
                    BPCRINTM.RETURN_INFO = 'N';
                }
                C100_OUTPUT_RINTM();
                if (pgmRtn) return;
            }
        } else if (BPCRINTM.FUNC == 'U') {
            B250_UPDATE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B210_QUERY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        IBS.init(SCCGWA, BPRINTRT);
        BPRINTR.KEY.BR = BPCRINTM.BR;
        BPRINTR.KEY.CCY = BPCRINTM.CCY;
        BPRINTR.KEY.BASE_TYP = BPCRINTM.BASE_TYP;
        BPRINTR.KEY.TENOR = BPCRINTM.TENOR;
        BPRINTR.KEY.DT = BPCRINTM.DT;
        CEP.TRC(SCCGWA, BPRINTR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRINTR.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRINTR.KEY.TENOR);
        CEP.TRC(SCCGWA, BPRINTR.KEY.DT);
        CEP.TRC(SCCGWA, BPRINTR.KEY.BR);
        CEP.TRC(SCCGWA, "11111111");
        T000_READ_BPTINTR();
        if (pgmRtn) return;
        if (WS_RESULT_FLAG == 'N') {
            IBS.CLONE(SCCGWA, BPRINTR, BPRINTRT);
        }
    }
    public void B220_ADD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTM.BR;
        BPRINTR.KEY.CCY = BPCRINTM.CCY;
        BPRINTR.KEY.BASE_TYP = BPCRINTM.BASE_TYP;
        BPRINTR.KEY.TENOR = BPCRINTM.TENOR;
        BPRINTR.KEY.DT = BPCRINTM.DT;
        BPRINTR.TM = BPCRINTM.TM;
        BPRINTR.REF_BR = BPCRINTM.REF_BR;
        BPRINTR.REF_CCY = BPCRINTM.REF_CCY;
        BPRINTR.REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
        BPRINTR.REF_TENOR = BPCRINTM.REF_TENOR;
        BPRINTR.FORMAT = BPCRINTM.FORMAT;
        BPRINTR.DIFF = BPCRINTM.DIFF;
        BPRINTR.RATE = BPCRINTM.RATE;
        BPRINTR.REF_DEPTH = BPCRINTM.REF_DEPTH;
        BPRINTR.TELLER = BPCRINTM.TELLER;
        BPRINTR.OVR1 = BPCRINTM.OVR1;
        BPRINTR.OVR2 = BPCRINTM.OVR2;
        CEP.TRC(SCCGWA, BPRINTR.RATE);
        CEP.TRC(SCCGWA, "RINTMYWS");
        T000_WRITE_BPTINTR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRINTR.RATE);
        CEP.TRC(SCCGWA, "RINTMYWS");
    }
    public void B230_DELETE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTM.BR;
        BPRINTR.KEY.CCY = BPCRINTM.CCY;
        BPRINTR.KEY.BASE_TYP = BPCRINTM.BASE_TYP;
        BPRINTR.KEY.TENOR = BPCRINTM.TENOR;
        BPRINTR.KEY.DT = BPRINTRT.KEY.DT;
        CEP.TRC(SCCGWA, BPRINTR.KEY.BR);
        CEP.TRC(SCCGWA, BPRINTR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRINTR.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRINTR.KEY.TENOR);
        CEP.TRC(SCCGWA, BPRINTR.KEY.DT);
        T000_READ_BPTINTR_UPD();
        if (pgmRtn) return;
        T000_DELETE_BPTINTR();
        if (pgmRtn) return;
    }
    public void B240_READUP_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTM.BR;
        BPRINTR.KEY.CCY = BPCRINTM.CCY;
        BPRINTR.KEY.BASE_TYP = BPCRINTM.BASE_TYP;
        BPRINTR.KEY.TENOR = BPCRINTM.TENOR;
        BPRINTR.KEY.DT = BPRINTRT.KEY.DT;
        CEP.TRC(SCCGWA, BPRINTR.KEY.DT);
        T000_READ_BPTINTR_UPD();
        if (pgmRtn) return;
        BPCRINTM.DT = BPRINTR.KEY.DT;
        BPCRINTM.TM = BPRINTR.TM;
        BPCRINTM.REF_BR = BPRINTR.REF_BR;
        BPCRINTM.REF_CCY = BPRINTR.REF_CCY;
        BPCRINTM.REF_BASE_TYP = BPRINTR.REF_BASE_TYP;
        BPCRINTM.REF_TENOR = BPRINTR.REF_TENOR;
        BPCRINTM.FORMAT = BPRINTR.FORMAT;
        BPCRINTM.DIFF = BPRINTR.DIFF;
        BPCRINTM.RATE = BPRINTR.RATE;
        BPCRINTM.REF_DEPTH = BPRINTR.REF_DEPTH;
        BPCRINTM.TELLER = BPRINTR.TELLER;
        BPCRINTM.OVR1 = BPRINTR.OVR1;
        BPCRINTM.OVR2 = BPRINTR.OVR2;
    }
    public void B250_UPDATE_RECORD_PROC() throws IOException,SQLException,Exception {
        T000_DELETE_BPTINTR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTM.BR;
        BPRINTR.KEY.CCY = BPCRINTM.CCY;
        BPRINTR.KEY.BASE_TYP = BPCRINTM.BASE_TYP;
        BPRINTR.KEY.TENOR = BPCRINTM.TENOR;
        BPRINTR.KEY.DT = BPCRINTM.DT;
        BPRINTR.TM = BPCRINTM.TM;
        BPRINTR.REF_BR = BPCRINTM.REF_BR;
        BPRINTR.REF_CCY = BPCRINTM.REF_CCY;
        BPRINTR.REF_BASE_TYP = BPCRINTM.REF_BASE_TYP;
        BPRINTR.REF_TENOR = BPCRINTM.REF_TENOR;
        BPRINTR.FORMAT = BPCRINTM.FORMAT;
        BPRINTR.DIFF = BPCRINTM.DIFF;
        BPRINTR.RATE = BPCRINTM.RATE;
        BPRINTR.REF_DEPTH = BPCRINTM.REF_DEPTH;
        BPRINTR.TELLER = BPCRINTM.TELLER;
        BPRINTR.OVR1 = BPCRINTM.OVR1;
        BPRINTR.OVR2 = BPCRINTM.OVR2;
        T000_WRITE_BPTINTR();
        if (pgmRtn) return;
    }
    public void T000_READ_BPTINTR() throws IOException,SQLException,Exception {
        BPTINTR_RD = new DBParm();
        BPTINTR_RD.TableName = "BPTINTR";
        BPTINTR_RD.where = "BR = :BPRINTR.KEY.BR "
            + "AND CCY = :BPRINTR.KEY.CCY "
            + "AND BASE_TYP = :BPRINTR.KEY.BASE_TYP "
            + "AND TENOR = :BPRINTR.KEY.TENOR "
            + "AND DT <= :BPRINTR.KEY.DT";
        BPTINTR_RD.fst = true;
        BPTINTR_RD.order = "DT DESC";
        IBS.READ(SCCGWA, BPRINTR, this, BPTINTR_RD);
        CEP.TRC(SCCGWA, BPRINTR.KEY.DT);
        CEP.TRC(SCCGWA, BPRINTR.KEY.BR);
        CEP.TRC(SCCGWA, BPRINTR.RATE);
        CEP.TRC(SCCGWA, BPRINTR.KEY.CCY);
        CEP.TRC(SCCGWA, BPRINTR.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRINTR.KEY.TENOR);
        CEP.TRC(SCCGWA, "JANELY");
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RESULT_FLAG = 'N';
            BPCRINTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_RESULT_FLAG = 'O';
            BPCRINTM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void C100_OUTPUT_RINTM() throws IOException,SQLException,Exception {
        BPCRINTM.DT = BPRINTRT.KEY.DT;
        BPCRINTM.TM = BPRINTRT.TM;
        BPCRINTM.REF_BR = BPRINTRT.REF_BR;
        CEP.TRC(SCCGWA, "&&&&&");
        CEP.TRC(SCCGWA, BPRINTRT.REF_BR);
        BPCRINTM.REF_CCY = BPRINTRT.REF_CCY;
        CEP.TRC(SCCGWA, BPRINTRT.REF_CCY);
        BPCRINTM.REF_BASE_TYP = BPRINTRT.REF_BASE_TYP;
        CEP.TRC(SCCGWA, BPRINTRT.REF_BASE_TYP);
        BPCRINTM.REF_TENOR = BPRINTRT.REF_TENOR;
        CEP.TRC(SCCGWA, BPRINTRT.REF_TENOR);
        BPCRINTM.FORMAT = BPRINTRT.FORMAT;
        BPCRINTM.DIFF = BPRINTRT.DIFF;
        BPCRINTM.RATE = BPRINTRT.RATE;
        BPCRINTM.REF_DEPTH = BPRINTRT.REF_DEPTH;
        BPCRINTM.TELLER = BPRINTRT.TELLER;
        BPCRINTM.OVR1 = BPRINTRT.OVR1;
        BPCRINTM.OVR2 = BPRINTRT.OVR2;
        CEP.TRC(SCCGWA, BPCRINTM.REF_TENOR);
        CEP.TRC(SCCGWA, "YWSKOCOCP");
    }
    public void T000_WRITE_BPTINTR() throws IOException,SQLException,Exception {
        BPTINTR_RD = new DBParm();
        BPTINTR_RD.TableName = "BPTINTR";
        IBS.WRITE(SCCGWA, BPRINTR, BPTINTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            CEP.TRC(SCCGWA, "ZHENGJIE11");
            WS_RESULT_FLAG = 'N';
            BPCRINTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.TRC(SCCGWA, "ZHENGJIE12");
            BPCRINTM.RETURN_INFO = 'D';
        } else {
            CEP.TRC(SCCGWA, "ZHENGJIE13");
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTINTR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_DELETE_BPTINTR() throws IOException,SQLException,Exception {
        BPTINTR_RD = new DBParm();
        BPTINTR_RD.TableName = "BPTINTR";
        BPTINTR_RD.errhdl = true;
        IBS.DELETE(SCCGWA, BPRINTR, BPTINTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_RESULT_FLAG = 'N';
            BPCRINTM.RETURN_INFO = 'F';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTINTR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "DELETE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_BPTINTR() throws IOException,SQLException,Exception {
        BPTINTR_RD = new DBParm();
        BPTINTR_RD.TableName = "BPTINTR";
        BPTINTR_RD.errhdl = true;
        IBS.REWRITE(SCCGWA, BPRINTR, BPTINTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRINTM.RETURN_INFO = 'F';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTINTR;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTINTR_UPD() throws IOException,SQLException,Exception {
        BPTINTR_RD = new DBParm();
        BPTINTR_RD.TableName = "BPTINTR";
        BPTINTR_RD.upd = true;
        BPTINTR_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRINTR, BPTINTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            BPCRINTM.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            BPCRINTM.RETURN_INFO = 'N';
        } else {
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRINTM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRINTM = ");
            CEP.TRC(SCCGWA, BPCRINTM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
