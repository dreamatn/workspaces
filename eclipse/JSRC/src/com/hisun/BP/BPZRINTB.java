package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRINTB {
    brParm BPTINTR_BR = new brParm();
    boolean pgmRtn = false;
    String TBL_BPTINTR = "BPTINTR ";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_RECORD_NUMBER = 0;
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBANK BPRBANK = new BPRBANK();
    BPRINTR BPRINTR = new BPRINTR();
    SCCGWA SCCGWA;
    BPCRINTB BPCRINTB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCRINTB BPCRINTB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRINTB = BPCRINTB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRINTB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRINTB.RC);
        IBS.init(SCCGWA, BPRINTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAINT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPCRINTB.FUNC != 'S' 
            && BPCRINTB.FUNC != 'N' 
            && BPCRINTB.FUNC != 'E')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR, BPCRINTB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B200_MAINT_PROCESS() throws IOException,SQLException,Exception {
        if (BPCRINTB.FUNC == 'S') {
            B210_START_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (BPCRINTB.FUNC == 'N') {
            B220_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRINTB.FUNC == 'E') {
            B230_END_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR, BPCRINTB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_START_BROWSE_PROC() throws IOException,SQLException,Exception {
        if (BPCRINTB.COND == '1') {
            B211_BROWSE_BR();
            if (pgmRtn) return;
        } else if (BPCRINTB.COND == '2') {
            B212_BROWSE_BR_CCY();
            if (pgmRtn) return;
        } else if (BPCRINTB.COND == '3') {
            B213_BROWSE_BR_BASETYP();
            if (pgmRtn) return;
        } else if (BPCRINTB.COND == '4') {
            B214_BROWSE_BR_TENOR();
            if (pgmRtn) return;
        } else if (BPCRINTB.COND == '5') {
            B215_BROWSE_BR_CCY_BASETYP();
            if (pgmRtn) return;
        } else if (BPCRINTB.COND == '6') {
            B216_BROWSE_BR_CCY_TENOR();
            if (pgmRtn) return;
        } else if (BPCRINTB.COND == '7') {
            B217_BROWSE_BR_BASETYP_TENOR();
            if (pgmRtn) return;
        } else if (BPCRINTB.COND == '8') {
            B218_BROWSE_BR_ALL();
            if (pgmRtn) return;
        } else if (BPCRINTB.COND == '9') {
            B218_BROWSE_BR_PRI_ALL();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR, BPCRINTB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B211_BROWSE_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTB.BR;
        T000_BROWSE_BPTINTR_1();
        if (pgmRtn) return;
    }
    public void B212_BROWSE_BR_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTB.BR;
        BPRINTR.KEY.CCY = BPCRINTB.CCY;
        T000_BROWSE_BPTINTR_2();
        if (pgmRtn) return;
    }
    public void B213_BROWSE_BR_BASETYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTB.BR;
        BPRINTR.KEY.BASE_TYP = BPCRINTB.BASE_TYP;
        T000_BROWSE_BPTINTR_3();
        if (pgmRtn) return;
    }
    public void B214_BROWSE_BR_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTB.BR;
        BPRINTR.KEY.TENOR = BPCRINTB.TENOR;
        T000_BROWSE_BPTINTR_4();
        if (pgmRtn) return;
    }
    public void B215_BROWSE_BR_CCY_BASETYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTB.BR;
        BPRINTR.KEY.CCY = BPCRINTB.CCY;
        BPRINTR.KEY.BASE_TYP = BPCRINTB.BASE_TYP;
        T000_BROWSE_BPTINTR_5();
        if (pgmRtn) return;
    }
    public void B216_BROWSE_BR_CCY_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTB.BR;
        BPRINTR.KEY.CCY = BPCRINTB.CCY;
        BPRINTR.KEY.TENOR = BPCRINTB.TENOR;
        T000_BROWSE_BPTINTR_6();
        if (pgmRtn) return;
    }
    public void B217_BROWSE_BR_BASETYP_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTB.BR;
        BPRINTR.KEY.BASE_TYP = BPCRINTB.BASE_TYP;
        BPRINTR.KEY.TENOR = BPCRINTB.TENOR;
        T000_BROWSE_BPTINTR_7();
        if (pgmRtn) return;
    }
    public void B218_BROWSE_BR_ALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.REF_BR = BPCRINTB.REF_BR;
        BPRINTR.REF_CCY = BPCRINTB.REF_CCY;
        BPRINTR.REF_BASE_TYP = BPCRINTB.REF_BASE_TYP;
        BPRINTR.REF_TENOR = BPCRINTB.REF_TENOR;
        T000_BROWSE_BPTINTR_ORDERBY();
        if (pgmRtn) return;
    }
    public void B218_BROWSE_BR_PRI_ALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINTR);
        BPRINTR.KEY.BR = BPCRINTB.BR;
        BPRINTR.KEY.CCY = BPCRINTB.CCY;
        BPRINTR.KEY.BASE_TYP = BPCRINTB.BASE_TYP;
        BPRINTR.KEY.TENOR = BPCRINTB.TENOR;
        T000_BROWSE_BPTINTR_PRI_ALL();
        if (pgmRtn) return;
    }
    public void B220_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTINTR();
        if (pgmRtn) return;
        BPCRINTB.BR = BPRINTR.KEY.BR;
        BPCRINTB.CCY = BPRINTR.KEY.CCY;
        BPCRINTB.BASE_TYP = BPRINTR.KEY.BASE_TYP;
        BPCRINTB.TENOR = BPRINTR.KEY.TENOR;
        BPCRINTB.DT = BPRINTR.KEY.DT;
        BPCRINTB.TM = BPRINTR.TM;
        BPCRINTB.REF_BR = BPRINTR.REF_BR;
        BPCRINTB.REF_CCY = BPRINTR.REF_CCY;
        BPCRINTB.REF_BASE_TYP = BPRINTR.REF_BASE_TYP;
        BPCRINTB.REF_TENOR = BPRINTR.REF_TENOR;
        BPCRINTB.FORMAT = BPRINTR.FORMAT;
        BPCRINTB.DIFF = BPRINTR.DIFF;
        BPCRINTB.RATE = BPRINTR.RATE;
        BPCRINTB.REF_DEPTH = BPRINTR.REF_DEPTH;
        BPCRINTB.TELLER = BPRINTR.TELLER;
        BPCRINTB.OVR1 = BPRINTR.OVR1;
        BPCRINTB.OVR2 = BPRINTR.OVR2;
    }
    public void B230_END_BROWSE_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTINTR();
        if (pgmRtn) return;
    }
    public void T000_BROWSE_BPTINTR_1() throws IOException,SQLException,Exception {
        BPTINTR_BR.rp = new DBParm();
        BPTINTR_BR.rp.TableName = "BPTINTR";
        BPTINTR_BR.rp.where = "BR = :BPRINTR.KEY.BR";
        IBS.STARTBR(SCCGWA, BPRINTR, this, BPTINTR_BR);
    }
    public void T000_BROWSE_BPTINTR_2() throws IOException,SQLException,Exception {
        BPTINTR_BR.rp = new DBParm();
        BPTINTR_BR.rp.TableName = "BPTINTR";
        BPTINTR_BR.rp.where = "BR = :BPRINTR.KEY.BR "
            + "AND CCY = :BPRINTR.KEY.CCY";
        IBS.STARTBR(SCCGWA, BPRINTR, this, BPTINTR_BR);
    }
    public void T000_BROWSE_BPTINTR_3() throws IOException,SQLException,Exception {
        BPTINTR_BR.rp = new DBParm();
        BPTINTR_BR.rp.TableName = "BPTINTR";
        BPTINTR_BR.rp.where = "BR = :BPRINTR.KEY.BR "
            + "AND BASE_TYP = :BPRINTR.KEY.BASE_TYP";
        IBS.STARTBR(SCCGWA, BPRINTR, this, BPTINTR_BR);
    }
    public void T000_BROWSE_BPTINTR_4() throws IOException,SQLException,Exception {
        BPTINTR_BR.rp = new DBParm();
        BPTINTR_BR.rp.TableName = "BPTINTR";
        BPTINTR_BR.rp.where = "BR = :BPRINTR.KEY.BR "
            + "AND TENOR = :BPRINTR.KEY.TENOR";
        IBS.STARTBR(SCCGWA, BPRINTR, this, BPTINTR_BR);
    }
    public void T000_BROWSE_BPTINTR_5() throws IOException,SQLException,Exception {
        BPTINTR_BR.rp = new DBParm();
        BPTINTR_BR.rp.TableName = "BPTINTR";
        BPTINTR_BR.rp.where = "BR = :BPRINTR.KEY.BR "
            + "AND CCY = :BPRINTR.KEY.CCY "
            + "AND BASE_TYP = :BPRINTR.KEY.BASE_TYP";
        IBS.STARTBR(SCCGWA, BPRINTR, this, BPTINTR_BR);
    }
    public void T000_BROWSE_BPTINTR_6() throws IOException,SQLException,Exception {
        BPTINTR_BR.rp = new DBParm();
        BPTINTR_BR.rp.TableName = "BPTINTR";
        BPTINTR_BR.rp.where = "BR = :BPRINTR.KEY.BR "
            + "AND CCY = :BPRINTR.KEY.CCY "
            + "AND TENOR = :BPRINTR.KEY.TENOR";
        IBS.STARTBR(SCCGWA, BPRINTR, this, BPTINTR_BR);
    }
    public void T000_BROWSE_BPTINTR_7() throws IOException,SQLException,Exception {
        BPTINTR_BR.rp = new DBParm();
        BPTINTR_BR.rp.TableName = "BPTINTR";
        BPTINTR_BR.rp.where = "BR = :BPRINTR.KEY.BR "
            + "AND BASE_TYP = :BPRINTR.KEY.BASE_TYP "
            + "AND TENOR = :BPRINTR.KEY.TENOR";
        IBS.STARTBR(SCCGWA, BPRINTR, this, BPTINTR_BR);
    }
    public void T000_BROWSE_BPTINTR_ORDERBY() throws IOException,SQLException,Exception {
        BPTINTR_BR.rp = new DBParm();
        BPTINTR_BR.rp.TableName = "BPTINTR";
        BPTINTR_BR.rp.where = "REF_BR = :BPRINTR.REF_BR "
            + "AND REF_CCY = :BPRINTR.REF_CCY "
            + "AND REF_BASE_TYP = :BPRINTR.REF_BASE_TYP "
            + "AND REF_TENOR = :BPRINTR.REF_TENOR";
        BPTINTR_BR.rp.order = "REF_DEPTH DESC";
        IBS.STARTBR(SCCGWA, BPRINTR, this, BPTINTR_BR);
    }
    public void T000_BROWSE_BPTINTR_PRI_ALL() throws IOException,SQLException,Exception {
        BPTINTR_BR.rp = new DBParm();
        BPTINTR_BR.rp.TableName = "BPTINTR";
        BPTINTR_BR.rp.where = "BR = :BPRINTR.KEY.BR "
            + "AND CCY = :BPRINTR.KEY.CCY "
            + "AND BASE_TYP = :BPRINTR.KEY.BASE_TYP "
            + "AND TENOR = :BPRINTR.KEY.TENOR";
        IBS.STARTBR(SCCGWA, BPRINTR, this, BPTINTR_BR);
    }
    public void T000_READNEXT_BPTINTR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRINTR, this, BPTINTR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRINTB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_ENDBR_BPTINTR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTINTR_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRINTB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRINTB = ");
            CEP.TRC(SCCGWA, BPCRINTB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
