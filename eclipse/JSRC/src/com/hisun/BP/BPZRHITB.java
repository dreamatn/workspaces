package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZRHITB {
    brParm BPTIHIT_BR = new brParm();
    DBParm BPTIHIT_RD;
    boolean pgmRtn = false;
    String TBL_BPTIHIT = "BPTIHIT ";
    String WS_ERR_MSG = " ";
    short WS_I = 0;
    short WS_RECORD_NUMBER = 0;
    int WS_STARTD = 0;
    int WS_ENDD = 0;
    char WS_TBL_BANK_FLAG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRBANK BPRBANK = new BPRBANK();
    BPRIHIT BPRIHIT = new BPRIHIT();
    SCCGWA SCCGWA;
    BPCRHITB BPCRHITB;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCRHITB BPCRHITB) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRHITB = BPCRHITB;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZRHITB return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.SC_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCRHITB.RC);
        IBS.init(SCCGWA, BPRIHIT);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAINT_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if ((BPCRHITB.FUNC != 'S' 
            && BPCRHITB.FUNC != 'N' 
            && BPCRHITB.FUNC != 'E' 
            && BPCRHITB.FUNC != 'R')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_FUNC_ERROR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_STARTD = BPCRHITB.STARTD;
        WS_ENDD = BPCRHITB.ENDD;
    }
    public void B200_MAINT_PROCESS() throws IOException,SQLException,Exception {
        if (BPCRHITB.FUNC == 'S') {
            B210_START_READ_PROC();
            if (pgmRtn) return;
        } else if (BPCRHITB.FUNC == 'N') {
            B220_READ_NEXT_PROC();
            if (pgmRtn) return;
        } else if (BPCRHITB.FUNC == 'E') {
            B230_END_READ_PROC();
            if (pgmRtn) return;
        } else if (BPCRHITB.FUNC == 'R') {
            B240_READ_FISRT_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B210_START_READ_PROC() throws IOException,SQLException,Exception {
        if (BPCRHITB.COND == '1') {
            B211_READ_BR();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '2') {
            B212_READ_BR_CCY();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '3') {
            B213_READ_BR_BASETYP();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '4') {
            B214_READ_BR_TENOR();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '5') {
            B215_READ_BR_CCY_BASETYP();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '6') {
            B216_READ_BR_CCY_TENOR();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '7') {
            B217_READ_BR_BASETYP_TENOR();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '8') {
            B218_READ_BR_ALL();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == 'C') {
            B218_READ_BR_ALL_DESC();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '9') {
            B219_READ_BR_ALL_FST();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == 'A') {
            B220_READ_BR_ALL_UPD();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == 'D') {
            B221_READ_BR_TODAY();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B211_READ_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        T000_BRW_BPTIHIT_1();
        if (pgmRtn) return;
    }
    public void B212_READ_BR_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        T000_BRW_BPTIHIT_2();
        if (pgmRtn) return;
    }
    public void B213_READ_BR_BASETYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        CEP.TRC(SCCGWA, BPCRHITB.BR);
        CEP.TRC(SCCGWA, BPCRHITB.BASE_TYP);
        T000_BRW_BPTIHIT_3();
        if (pgmRtn) return;
    }
    public void B214_READ_BR_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_BRW_BPTIHIT_4();
        if (pgmRtn) return;
    }
    public void B215_READ_BR_CCY_BASETYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        T000_BRW_BPTIHIT_5();
        if (pgmRtn) return;
    }
    public void B216_READ_BR_CCY_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_BRW_BPTIHIT_6();
        if (pgmRtn) return;
    }
    public void B217_READ_BR_BASETYP_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_BRW_BPTIHIT_7();
        if (pgmRtn) return;
    }
    public void B218_READ_BR_ALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_BRW_BPTIHIT_8();
        if (pgmRtn) return;
    }
    public void B218_READ_BR_ALL_DESC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_BRW_BPTIHIT_10();
        if (pgmRtn) return;
    }
    public void B219_READ_BR_ALL_FST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_BRW_BPTIHIT_9();
        if (pgmRtn) return;
    }
    public void B220_READ_BR_ALL_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_BRW_BPTIHIT_A();
        if (pgmRtn) return;
    }
    public void B221_READ_BR_TODAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_BRW_BPTIHIT_D();
        if (pgmRtn) return;
    }
    public void B220_READ_NEXT_PROC() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTIHIT();
        if (pgmRtn) return;
        BPCRHITB.BR = BPRIHIT.KEY.BR;
        BPCRHITB.CCY = BPRIHIT.KEY.CCY;
        BPCRHITB.BASE_TYP = BPRIHIT.KEY.BASE_TYP;
        BPCRHITB.TENOR = BPRIHIT.KEY.TENOR;
        BPCRHITB.DT = BPRIHIT.KEY.DT;
        BPCRHITB.TM = BPRIHIT.TM;
        BPCRHITB.REF_BR = BPRIHIT.REF_BR;
        BPCRHITB.REF_CCY = BPRIHIT.REF_CCY;
        BPCRHITB.REF_BASE_TYP = BPRIHIT.REF_BASE_TYP;
        BPCRHITB.REF_TENOR = BPRIHIT.REF_TENOR;
        BPCRHITB.FORMAT = BPRIHIT.FORMAT;
        BPCRHITB.DIFF = BPRIHIT.DIFF;
        BPCRHITB.RATE = BPRIHIT.RATE;
        BPCRHITB.REF_DEPTH = BPRIHIT.REF_DEPTH;
        BPCRHITB.TELLER = BPRIHIT.TELLER;
        BPCRHITB.OVR1 = BPRIHIT.OVR1;
        BPCRHITB.OVR2 = BPRIHIT.OVR2;
        BPCRHITB.TS = BPRIHIT.TS;
    }
    public void B230_END_READ_PROC() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTIHIT();
        if (pgmRtn) return;
    }
    public void B240_READ_FISRT_PROC() throws IOException,SQLException,Exception {
        if (BPCRHITB.COND == '1') {
            B211_READ_FIRST_BR();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '2') {
            B212_READ_FIRST_BR_CCY();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '3') {
            B213_READ_FIRST_BR_BASETYP();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '4') {
            B214_READ_FIRST_BR_TENOR();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '5') {
            B215_READ_FIRST_BR_CCY_BASETYP();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '6') {
            B216_READ_FIRST_BR_CCY_TENOR();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '7') {
            B217_READ_FRT_BR_BASETYP_TENOR();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '8') {
            B218_READ_FIRST_BR_ALL();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == 'C') {
            B218_READ_FIRST_BR_ALL_DESC();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == '9') {
            B219_READ_FIRST_BR_ALL_FST();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == 'A') {
            B220_READ_FIRST_BR_ALL_UPD();
            if (pgmRtn) return;
        } else if (BPCRHITB.COND == 'D') {
            B221_READ_FIRST_BR_TODAY();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_INPUT_ERROR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B211_READ_FIRST_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        T000_READ_BPTIHIT_1();
        if (pgmRtn) return;
    }
    public void B212_READ_FIRST_BR_CCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        T000_READ_BPTIHIT_2();
        if (pgmRtn) return;
    }
    public void B213_READ_FIRST_BR_BASETYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        CEP.TRC(SCCGWA, BPCRHITB.BR);
        CEP.TRC(SCCGWA, BPCRHITB.BASE_TYP);
        T000_READ_BPTIHIT_3();
        if (pgmRtn) return;
    }
    public void B214_READ_FIRST_BR_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_READ_BPTIHIT_4();
        if (pgmRtn) return;
    }
    public void B215_READ_FIRST_BR_CCY_BASETYP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        T000_READ_BPTIHIT_5();
        if (pgmRtn) return;
    }
    public void B216_READ_FIRST_BR_CCY_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_READ_BPTIHIT_6();
        if (pgmRtn) return;
    }
    public void B217_READ_FRT_BR_BASETYP_TENOR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_READ_BPTIHIT_7();
        if (pgmRtn) return;
    }
    public void B218_READ_FIRST_BR_ALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_READ_BPTIHIT_8();
        if (pgmRtn) return;
    }
    public void B218_READ_FIRST_BR_ALL_DESC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_READ_BPTIHIT_10();
        if (pgmRtn) return;
    }
    public void B219_READ_FIRST_BR_ALL_FST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_READ_BPTIHIT_9();
        if (pgmRtn) return;
    }
    public void B220_READ_FIRST_BR_ALL_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_READ_BPTIHIT_A();
        if (pgmRtn) return;
    }
    public void B221_READ_FIRST_BR_TODAY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRIHIT);
        BPRIHIT.KEY.BR = BPCRHITB.BR;
        BPRIHIT.KEY.CCY = BPCRHITB.CCY;
        BPRIHIT.KEY.BASE_TYP = BPCRHITB.BASE_TYP;
        BPRIHIT.KEY.TENOR = BPCRHITB.TENOR;
        T000_READ_BPTIHIT_D();
        if (pgmRtn) return;
    }
    public void T000_BRW_BPTIHIT_1() throws IOException,SQLException,Exception {
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_BRW_BPTIHIT_2() throws IOException,SQLException,Exception {
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_BRW_BPTIHIT_3() throws IOException,SQLException,Exception {
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_BRW_BPTIHIT_4() throws IOException,SQLException,Exception {
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_BRW_BPTIHIT_5() throws IOException,SQLException,Exception {
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_BRW_BPTIHIT_6() throws IOException,SQLException,Exception {
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_BRW_BPTIHIT_7() throws IOException,SQLException,Exception {
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_BRW_BPTIHIT_8() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRIHIT.KEY.BR);
        CEP.TRC(SCCGWA, BPRIHIT.KEY.CCY);
        CEP.TRC(SCCGWA, BPRIHIT.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRIHIT.KEY.TENOR);
        CEP.TRC(SCCGWA, WS_STARTD);
        CEP.TRC(SCCGWA, WS_ENDD);
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_BRW_BPTIHIT_9() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT < :WS_STARTD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT DESC";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            BPCRHITB.BR = BPRIHIT.KEY.BR;
            BPCRHITB.CCY = BPRIHIT.KEY.CCY;
            BPCRHITB.BASE_TYP = BPRIHIT.KEY.BASE_TYP;
            BPCRHITB.TENOR = BPRIHIT.KEY.TENOR;
            BPCRHITB.DT = BPRIHIT.KEY.DT;
            BPCRHITB.TM = BPRIHIT.TM;
            BPCRHITB.REF_BR = BPRIHIT.REF_BR;
            BPCRHITB.REF_CCY = BPRIHIT.REF_CCY;
            BPCRHITB.REF_BASE_TYP = BPRIHIT.REF_BASE_TYP;
            BPCRHITB.REF_TENOR = BPRIHIT.REF_TENOR;
            BPCRHITB.FORMAT = BPRIHIT.FORMAT;
            BPCRHITB.DIFF = BPRIHIT.DIFF;
            BPCRHITB.RATE = BPRIHIT.RATE;
            BPCRHITB.REF_DEPTH = BPRIHIT.REF_DEPTH;
            BPCRHITB.TELLER = BPRIHIT.TELLER;
            BPCRHITB.OVR1 = BPRIHIT.OVR1;
            BPCRHITB.OVR2 = BPRIHIT.OVR2;
        }
    }
    public void T000_BRW_BPTIHIT_10() throws IOException,SQLException,Exception {
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR";
        BPTIHIT_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,DT DESC";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_BRW_BPTIHIT_A() throws IOException,SQLException,Exception {
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT > :WS_STARTD";
        BPTIHIT_BR.rp.upd = true;
        BPTIHIT_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,DT DESC";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_BRW_BPTIHIT_D() throws IOException,SQLException,Exception {
        BPTIHIT_BR.rp = new DBParm();
        BPTIHIT_BR.rp.TableName = "BPTIHIT";
        BPTIHIT_BR.rp.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT = :WS_STARTD";
        BPTIHIT_BR.rp.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.STARTBR(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_DBERR, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READNEXT_BPTIHIT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRIHIT, this, BPTIHIT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_ENDBR_BPTIHIT() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTIHIT_BR);
    }
    public void T000_READ_BPTIHIT_1() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BPTIHIT_2() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BPTIHIT_3() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BPTIHIT_4() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BPTIHIT_5() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BPTIHIT_6() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BPTIHIT_7() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BPTIHIT_8() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRIHIT.KEY.BR);
        CEP.TRC(SCCGWA, BPRIHIT.KEY.CCY);
        CEP.TRC(SCCGWA, BPRIHIT.KEY.BASE_TYP);
        CEP.TRC(SCCGWA, BPRIHIT.KEY.TENOR);
        CEP.TRC(SCCGWA, WS_STARTD);
        CEP.TRC(SCCGWA, WS_ENDD);
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT >= :WS_STARTD "
            + "AND DT <= :WS_ENDD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BPTIHIT_9() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT < :WS_STARTD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT DESC";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BPTIHIT_10() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT DESC";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BPTIHIT_A() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT > :WS_STARTD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT DESC";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void T000_READ_BPTIHIT_D() throws IOException,SQLException,Exception {
        BPTIHIT_RD = new DBParm();
        BPTIHIT_RD.TableName = "BPTIHIT";
        BPTIHIT_RD.where = "BR = :BPRIHIT.KEY.BR "
            + "AND CCY = :BPRIHIT.KEY.CCY "
            + "AND BASE_TYP = :BPRIHIT.KEY.BASE_TYP "
            + "AND TENOR = :BPRIHIT.KEY.TENOR "
            + "AND DT = :WS_STARTD";
        BPTIHIT_RD.fst = true;
        BPTIHIT_RD.order = "BR,CCY,BASE_TYP,TENOR,DT";
        IBS.READ(SCCGWA, BPRIHIT, this, BPTIHIT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INT_RECORD_NOT_EXIST, BPCRHITB.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRHITB.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCRHITB = ");
            CEP.TRC(SCCGWA, BPCRHITB);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
