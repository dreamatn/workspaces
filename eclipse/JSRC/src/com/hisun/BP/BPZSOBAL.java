package com.hisun.BP;

import com.hisun.AI.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSOBAL {
    DBParm BPTFHIST_RD;
    DBParm BPTFHIS_RD;
    DBParm BPTFHISA_RD;
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZSOBAL";
    String K_TBL_FHIST = "BPTFHIST";
    String PGM_SCSSCLDT = "SCSSCLDT";
    double WS_BAL_B = 0;
    double WS_BAL_D = 0;
    double WS_BAL_J = 0;
    int WS_LAST_BAL_DT = 0;
    String WS_ERR_MSG = " ";
    double WS_BAL_T = 0;
    char WS_DRCRFLG = ' ';
    char WS_STS = ' ';
    String WS_AP_MMO = " ";
    String WS_CCY = " ";
    char WS_CCY_TYP = ' ';
    char WS_BPTFHIST_FLG = ' ';
    char WS_BPTFHIS_FLG = ' ';
    char WS_BPTFHISA_FLG = ' ';
    char WS_TOD_FLG = ' ';
    char WS_JUMP_FLG = ' ';
    char WS_DATA_OVERFLOW_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPRFHIST BPRFHIST = new BPRFHIST();
    BPRFHISA BPRFHISA = new BPRFHISA();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AIRCMIB AIRCMIB = new AIRCMIB();
    AIRMIB AIRMIB = new AIRMIB();
    BPRFHIS BPRFHIS = new BPRFHIS();
    BPCOCLWD BPCOCLWD = new BPCOCLWD();
    SCCGWA SCCGWA;
    BPCSOBAL BPCSOBAL;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, BPCSOBAL BPCSOBAL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSOBAL = BPCSOBAL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSOBAL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCRBANK = (BPCPQBNK_DATA_INFO) SCCGWA.COMM_AREA.BANK_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, BPCSOBAL.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_CN();
        if (pgmRtn) return;
        B040_GET_AC_LSTBAL_CN();
        if (pgmRtn) return;
        B030_GET_AC_AMT_CN();
        if (pgmRtn) return;
        B050_COMPUTE_BAL();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_CN() throws IOException,SQLException,Exception {
        if (BPCSOBAL.INPUT.IN_DT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ENT_DATE_MUST_INPUT, BPCSOBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCSOBAL.INPUT.AC.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_AC_MUST_INPUT, BPCSOBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCSOBAL.INPUT.CCY.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_CCY_MUST_INPUT, BPCSOBAL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B080_CHECK_JUMP_CN() throws IOException,SQLException,Exception {
        WS_JUMP_FLG = 'Y';
        if (WS_LAST_BAL_DT > 0) {
            IBS.init(SCCGWA, BPCOCLWD);
            BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
            BPCOCLWD.DATE1 = WS_LAST_BAL_DT;
            BPCOCLWD.DATE2 = BPCSOBAL.INPUT.IN_DT;
            CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
            CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
            S000_CALL_BPZPCLWD();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
            if (BPCOCLWD.DAYS > 1) {
                WS_JUMP_FLG = 'N';
            }
        }
    }
    public void S000_CALL_BPZPCLWD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-CAL-WORK-DAY", BPCOCLWD);
        if (BPCOCLWD.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCBJPRM: BPZPCLWD ERROR ");
            CEP.TRC(SCCGWA, BPCOCLWD.RC.RC_CODE);
            SCCGWA.RETURN_CODE = 8;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B030_GET_AC_AMT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHIST);
        BPRFHIST.KEY.AC_DT = BPCSOBAL.INPUT.IN_DT;
        BPRFHIST.KEY.AC = BPCSOBAL.INPUT.AC;
        BPRFHIST.TX_CCY = BPCSOBAL.INPUT.CCY;
        BPRFHIST.TX_CCY_TYPE = BPCSOBAL.INPUT.CCY_TYPE;
        CEP.TRC(SCCGWA, WS_TOD_FLG);
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, WS_JUMP_FLG);
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, BPCSOBAL.INPUT.IN_DT);
        T040_GROUP_BPTFHIS_CN();
        if (pgmRtn) return;
        if (WS_BPTFHIS_FLG == 'N') {
            WS_BAL_D = 0;
            WS_BAL_B = 0;
        }
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, WS_BAL_B);
    }
    public void B040_GET_AC_LSTBAL_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFHISA);
        BPRFHISA.AC_DT = BPCSOBAL.INPUT.IN_DT;
        BPRFHISA.KEY.AC = BPCSOBAL.INPUT.AC;
        BPRFHISA.KEY.CCY = BPCSOBAL.INPUT.CCY;
        BPRFHISA.KEY.CCY_TYP = BPCSOBAL.INPUT.CCY_TYPE;
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY);
        CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY_TYP);
        T000_STARTBR_BPTFHISA_CN();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
        CEP.TRC(SCCGWA, WS_BPTFHISA_FLG);
        if (WS_BPTFHISA_FLG == 'Y') {
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
            if (BPCSOBAL.INPUT.IN_DT != SCCGWA.COMM_AREA.AC_DATE) {
                BPCSOBAL.OUTPUT.CUR_BAL = BPRFHISA.CUR_BAL;
                BPCSOBAL.RC.RC_CODE = 0;
                Z_RET();
                if (pgmRtn) return;
            } else {
                WS_BAL_T = BPRFHISA.CUR_BAL;
                WS_LAST_BAL_DT = BPRFHISA.AC_DT;
            }
        }
        if (WS_BPTFHISA_FLG == 'N') {
            BPCSOBAL.OUTPUT.CUR_BAL = 0;
            BPCSOBAL.RC.RC_CODE = 0;
        }
        CEP.TRC(SCCGWA, WS_BAL_T);
    }
    public void B050_COMPUTE_BAL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSOBAL.INPUT.AC);
        CEP.TRC(SCCGWA, WS_AP_MMO);
        WS_DATA_OVERFLOW_FLG = 'Y';
        BPCSOBAL.OUTPUT.CUR_BAL = WS_BAL_T - WS_BAL_D;
        if (WS_DATA_OVERFLOW_FLG == 'N') {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_DATA_OVERFLOW;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "THE RESULT IS :");
        CEP.TRC(SCCGWA, BPCSOBAL.OUTPUT.CUR_BAL);
    }
    public void B090_GET_LAST_AC_DT_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOCLWD);
        BPCOCLWD.CAL_CODE = BPCRBANK.CALD_BUI;
        BPCOCLWD.DATE1 = BPCSOBAL.INPUT.IN_DT;
        BPCOCLWD.DAYS = 1;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE1);
        CEP.TRC(SCCGWA, BPCOCLWD.DAYS);
        S000_CALL_BPZPCLWD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCOCLWD.DATE2);
    }
    public void B070_WHEN_CURBAL_ZERO() throws IOException,SQLException,Exception {
        if (WS_BAL_T == 0) {
            IBS.init(SCCGWA, BPRFHISA);
            BPRFHISA.AC_DT = BPCSOBAL.INPUT.IN_DT;
            BPRFHISA.KEY.AC = BPCSOBAL.INPUT.AC;
            BPRFHISA.KEY.CCY = BPCSOBAL.INPUT.CCY;
            BPRFHISA.KEY.CCY_TYP = BPCSOBAL.INPUT.CCY_TYPE;
            CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY);
            CEP.TRC(SCCGWA, BPRFHISA.KEY.CCY_TYP);
            T000_STARTBR_BPTFHISA_02();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, BPRFHISA.CUR_BAL);
            CEP.TRC(SCCGWA, WS_BPTFHISA_FLG);
            if (WS_BPTFHISA_FLG == 'Y') {
                WS_BAL_T = BPRFHISA.CUR_BAL;
                WS_LAST_BAL_DT = BPRFHISA.AC_DT;
            }
            if (WS_BPTFHISA_FLG == 'N') {
                BPCSOBAL.OUTPUT.CUR_BAL = 0;
                IBS.CPY2CLS(SCCGWA, 0+"", BPCSOBAL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void T000_READ_BPTFHIST() throws IOException,SQLException,Exception {
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        BPTFHIST_RD.where = "AC_DT = :BPRFHIST.KEY.AC_DT "
            + "AND JRNNO = :BPRFHIST.KEY.JRNNO "
            + "AND JRN_SEQ = :BPRFHIST.KEY.JRN_SEQ";
        BPTFHIST_RD.fst = true;
        IBS.READ(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTFHIST_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTFHIST_FLG = 'N';
        } else {
            SCCGWA.RETURN_CODE = 16;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_BPTFHIS_CN() throws IOException,SQLException,Exception {
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        BPTFHIS_RD.where = "AC_DT = :BPRFHIS.KEY.AC_DT "
            + "AND JRNNO = :BPRFHIS.KEY.JRNNO "
            + "AND JRN_SEQ = :BPRFHIS.KEY.JRN_SEQ";
        BPTFHIS_RD.fst = true;
        IBS.READ(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTFHIST_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTFHIST_FLG = 'N';
        } else {
            SCCGWA.RETURN_CODE = 16;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_BPTFHIST() throws IOException,SQLException,Exception {
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        BPTFHIST_RD.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,'C',-TX_AMT,0)),0)";
        BPTFHIST_RD.where = "AC = :BPRFHIST.KEY.AC "
            + "AND AC_DT = :BPRFHIST.KEY.AC_DT "
            + "AND ( JRNNO < :BPRFHIST.KEY.JRNNO "
            + "OR ( JRNNO = :BPRFHIST.KEY.JRNNO "
            + "AND JRN_SEQ <= :BPRFHIST.KEY.JRN_SEQ ) ) "
            + "AND TX_CCY = :BPRFHIST.TX_CCY";
        IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTFHIST_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTFHIST_FLG = 'N';
        } else {
            SCCGWA.RETURN_CODE = 16;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_BPTFHIST_CN() throws IOException,SQLException,Exception {
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        BPTFHIST_RD.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,'C',-TX_AMT,0)),0)";
        BPTFHIST_RD.where = "AC = :BPRFHIST.KEY.AC "
            + "AND AC_DT = :BPRFHIST.KEY.AC_DT "
            + "AND ( JRNNO < :BPRFHIST.KEY.JRNNO "
            + "OR ( JRNNO = :BPRFHIST.KEY.JRNNO "
            + "AND JRN_SEQ <= :BPRFHIST.KEY.JRN_SEQ ) ) "
            + "AND TX_CCY = :BPRFHIST.TX_CCY "
            + "AND TX_CCY_TYPE = :BPRFHIST.TX_CCY_TYPE";
        IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTFHIST_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTFHIST_FLG = 'N';
        } else {
            SCCGWA.RETURN_CODE = 16;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_GROUP_BPTFHIS_CN() throws IOException,SQLException,Exception {
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        BPTFHIS_RD.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,'C',-TX_AMT,0)),0)";
        BPTFHIS_RD.where = "AC = :BPRFHIST.KEY.AC "
            + "AND AC_DT = :BPRFHIST.KEY.AC_DT "
            + "AND ( JRNNO < :BPRFHIST.KEY.JRNNO "
            + "OR ( JRNNO = :BPRFHIST.KEY.JRNNO "
            + "AND JRN_SEQ <= :BPRFHIST.KEY.JRN_SEQ ) ) "
            + "AND TX_CCY = :BPRFHIST.TX_CCY "
            + "AND TX_CCY_TYPE = :BPRFHIST.TX_CCY_TYPE";
        IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTFHIST_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTFHIST_FLG = 'N';
        } else {
            SCCGWA.RETURN_CODE = 16;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T030_GROUP_BPTFHIST_JUMP_CN() throws IOException,SQLException,Exception {
        BPTFHIST_RD = new DBParm();
        BPTFHIST_RD.TableName = "BPTFHIST";
        BPTFHIST_RD.set = "WS-BAL-J=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,'C',-TX_AMT,0)),0)";
        BPTFHIST_RD.where = "AC = :BPRFHIST.KEY.AC "
            + "AND TX_CCY = :BPRFHIST.TX_CCY "
            + "AND TX_CCY_TYPE = :BPRFHIST.TX_CCY_TYPE "
            + "AND AC_DT <= :BPRFHIST.KEY.AC_DT "
            + "AND AC_DT > :WS_LAST_BAL_DT";
        BPTFHIST_RD.errhdl = true;
        IBS.GROUP(SCCGWA, BPRFHIST, this, BPTFHIST_RD);
        CEP.TRC(SCCGWA, WS_LAST_BAL_DT);
        CEP.TRC(SCCGWA, BPRFHIST.KEY.AC_DT);
        CEP.TRC(SCCGWA, WS_BAL_J);
        CEP.TRC(SCCGWA, WS_BAL_D);
        CEP.TRC(SCCGWA, WS_BAL_T);
    }
    public void T040_GROUP_BPTFHIS_CN() throws IOException,SQLException,Exception {
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        BPTFHIS_RD.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,'C',-TX_AMT,0)),0)";
        BPTFHIS_RD.where = "AC = :BPRFHIST.KEY.AC "
            + "AND TX_CCY = :BPRFHIST.TX_CCY "
            + "AND TX_CCY_TYPE = :BPRFHIST.TX_CCY_TYPE "
            + "AND AC_DT = :BPRFHIST.KEY.AC_DT";
        BPTFHIS_RD.errhdl = true;
        IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTFHIS_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTFHIS_FLG = 'N';
        } else {
            SCCGWA.RETURN_CODE = 16;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T050_GROUP_BPTFHIS2_CN() throws IOException,SQLException,Exception {
        BPTFHIS_RD = new DBParm();
        if (SCCGWA.COMM_AREA.JRN_IN_USE == '1') BPTFHIS_RD.TableName = "BPTFHIS1";
        else BPTFHIS_RD.TableName = "BPTFHIS2";
        BPTFHIS_RD.set = "WS-BAL-D=NVL(SUM(DECODE(DRCRFLG,'D',TX_AMT,'C',-TX_AMT,0)),0)";
        BPTFHIS_RD.where = "AC = :BPRFHIST.KEY.AC "
            + "AND TX_CCY = :BPRFHIST.TX_CCY "
            + "AND TX_CCY_TYPE = :BPRFHIST.TX_CCY_TYPE "
            + "AND AC_DT = :BPRFHIST.KEY.AC_DT";
        BPTFHIS_RD.errhdl = true;
        IBS.GROUP(SCCGWA, BPRFHIS, this, BPTFHIS_RD);
    }
    public void T000_STARTBR_BPTFHISA() throws IOException,SQLException,Exception {
        BPTFHISA_RD = new DBParm();
        BPTFHISA_RD.TableName = "BPTFHISA";
        BPTFHISA_RD.where = "AC = :BPRFHISA.KEY.AC "
            + "AND CCY = :BPRFHISA.KEY.CCY "
            + "AND AC_DT < :BPRFHISA.AC_DT";
        BPTFHISA_RD.fst = true;
        BPTFHISA_RD.order = "AC_DT DESC";
        IBS.READ(SCCGWA, BPRFHISA, this, BPTFHISA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTFHISA_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTFHISA_FLG = 'N';
        } else {
            SCCGWA.RETURN_CODE = 16;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTFHISA_CN() throws IOException,SQLException,Exception {
        BPTFHISA_RD = new DBParm();
        BPTFHISA_RD.TableName = "BPTFHISA";
        BPTFHISA_RD.where = "AC = :BPRFHISA.KEY.AC "
            + "AND CCY = :BPRFHISA.KEY.CCY "
            + "AND CCY_TYP = :BPRFHISA.KEY.CCY_TYP "
            + "AND AC_DT <= :BPRFHISA.AC_DT";
        BPTFHISA_RD.fst = true;
        BPTFHISA_RD.order = "AC_DT DESC";
        IBS.READ(SCCGWA, BPRFHISA, this, BPTFHISA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTFHISA_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTFHISA_FLG = 'N';
        } else {
            SCCGWA.RETURN_CODE = 16;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_BPTFHISA_02() throws IOException,SQLException,Exception {
        BPTFHISA_RD = new DBParm();
        BPTFHISA_RD.TableName = "BPTFHISA";
        BPTFHISA_RD.where = "AC = :BPRFHISA.KEY.AC "
            + "AND CCY = :BPRFHISA.KEY.CCY "
            + "AND CCY_TYP = :BPRFHISA.KEY.CCY_TYP "
            + "AND AC_DT < :BPRFHISA.AC_DT "
            + "AND CUR_BAL < > 0";
        BPTFHISA_RD.fst = true;
        BPTFHISA_RD.order = "AC_DT DESC";
        IBS.READ(SCCGWA, BPRFHISA, this, BPTFHISA_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BPTFHISA_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BPTFHISA_FLG = 'N';
        } else {
            SCCGWA.RETURN_CODE = 16;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCSOBAL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCSOBAL = ");
            CEP.TRC(SCCGWA, BPCSOBAL);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
