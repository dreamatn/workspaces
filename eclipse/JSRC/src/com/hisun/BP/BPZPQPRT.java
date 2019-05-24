package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQPRT {
    DBParm BPTPRTR_RD;
    boolean pgmRtn = false;
    String K_T_MAINT_PRDT_INFO = "BP-T-MAINT-PRDT-INFO";
    String K_T_MAINT_PRDT_MENU = "BP-T-MAINT-PRDT-MENU";
    char WS_PRTR = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPRPRTR BPRPRTR = new BPRPRTR();
    SCCGWA SCCGWA;
    BPCPQPRT BPCPQPRT;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPQPRT BPCPQPRT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQPRT = BPCPQPRT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQPRT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCPQPRT.RC);
        BPCPQPRT.RC.RC_AP = "BP";
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROC();
        if (pgmRtn) return;
        B300_MOVE_OUTPUT_DATA();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPQPRT.PRTR_NAME.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRT_NM_MST_INPUT, BPCPQPRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRT.PRTR_NAME);
        BPRPRTR.KEY.NAME = BPCPQPRT.PRTR_NAME;
        CEP.TRC(SCCGWA, BPRPRTR.KEY.NAME);
        T000_READ_BPTPRTR();
        if (pgmRtn) return;
        R000_CHECK_RETURN();
        if (pgmRtn) return;
    }
    public void R000_CHECK_RETURN() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_PRTR);
        if (WS_PRTR == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PRTR_REC_NOT_EXIST, BPCPQPRT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B300_MOVE_OUTPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQPRT.PRTR_NAME);
        BPCPQPRT.PRTR_NAME = BPRPRTR.KEY.NAME;
        BPCPQPRT.PRTR_ADDR1 = BPRPRTR.ADDRESS1;
        BPCPQPRT.PRTR_ADDR2 = BPRPRTR.ADDRESS1;
        BPCPQPRT.PRTR_ADDR3 = BPRPRTR.ADDRESS1;
        CEP.TRC(SCCGWA, "OUTPUT DATA:");
        CEP.TRC(SCCGWA, BPCPQPRT.PRTR_NAME);
        CEP.TRC(SCCGWA, BPCPQPRT.PRTR_ADDR1);
        CEP.TRC(SCCGWA, BPCPQPRT.PRTR_ADDR2);
        CEP.TRC(SCCGWA, BPCPQPRT.PRTR_ADDR3);
    }
    public void T000_READ_BPTPRTR() throws IOException,SQLException,Exception {
        BPTPRTR_RD = new DBParm();
        BPTPRTR_RD.TableName = "BPTPRTR";
        BPTPRTR_RD.errhdl = true;
        IBS.READ(SCCGWA, BPRPRTR, BPTPRTR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PRTR = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PRTR = 'N';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTPRTR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQPRT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQPRT = ");
            CEP.TRC(SCCGWA, BPCPQPRT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
