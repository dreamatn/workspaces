package com.hisun.CI;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZUSIZE {
    int JIBS_tmp_int;
    DBParm CITSIZE_RD;
    boolean pgmRtn = false;
    char WS_CHK_SIZE = ' ';
    String WS_INDUS = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    CIRSIZE CIRSIZE = new CIRSIZE();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICUSIZE CICUSIZE;
    public void MP(SCCGWA SCCGWA, CICUSIZE CICUSIZE) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICUSIZE = CICUSIZE;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZUSIZE return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICUSIZE.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_COMP_SIZE();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICUSIZE.DATA.INDUS);
        WS_INDUS = CICUSIZE.DATA.INDUS;
        IBS.init(SCCGWA, CIRSIZE);
        CIRSIZE.KEY.INDUS = WS_INDUS;
        T000_READ_CITSIZE_BY_INDUS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            if (WS_INDUS == null) WS_INDUS = "";
            JIBS_tmp_int = WS_INDUS.length();
            for (int i=0;i<5-JIBS_tmp_int;i++) WS_INDUS += " ";
            WS_INDUS = WS_INDUS.substring(0, 5 - 1) + "0" + WS_INDUS.substring(5 + 1 - 1);
            IBS.init(SCCGWA, CIRSIZE);
            CIRSIZE.KEY.INDUS = WS_INDUS;
            T000_READ_CITSIZE_BY_INDUS();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                if (WS_INDUS == null) WS_INDUS = "";
                JIBS_tmp_int = WS_INDUS.length();
                for (int i=0;i<5-JIBS_tmp_int;i++) WS_INDUS += " ";
                WS_INDUS = WS_INDUS.substring(0, 4 - 1) + "0" + WS_INDUS.substring(4 + 1 - 1);
                IBS.init(SCCGWA, CIRSIZE);
                CIRSIZE.KEY.INDUS = WS_INDUS;
                T000_READ_CITSIZE_BY_INDUS();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    if (WS_INDUS == null) WS_INDUS = "";
                    JIBS_tmp_int = WS_INDUS.length();
                    for (int i=0;i<5-JIBS_tmp_int;i++) WS_INDUS += " ";
                    WS_INDUS = WS_INDUS.substring(0, 3 - 1) + "0" + WS_INDUS.substring(3 + 1 - 1);
                    IBS.init(SCCGWA, CIRSIZE);
                    CIRSIZE.KEY.INDUS = WS_INDUS;
                    T000_READ_CITSIZE_BY_INDUS();
                    if (pgmRtn) return;
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                        if (WS_INDUS == null) WS_INDUS = "";
                        JIBS_tmp_int = WS_INDUS.length();
                        for (int i=0;i<5-JIBS_tmp_int;i++) WS_INDUS += " ";
                        WS_INDUS = WS_INDUS.substring(0, 2 - 1) + "0" + WS_INDUS.substring(2 + 1 - 1);
                        IBS.init(SCCGWA, CIRSIZE);
                        CIRSIZE.KEY.INDUS = WS_INDUS;
                        T000_READ_CITSIZE_BY_INDUS();
                        if (pgmRtn) return;
                        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                            if (WS_INDUS == null) WS_INDUS = "";
                            JIBS_tmp_int = WS_INDUS.length();
                            for (int i=0;i<5-JIBS_tmp_int;i++) WS_INDUS += " ";
                            WS_INDUS = "0" + WS_INDUS.substring(1);
                            IBS.init(SCCGWA, CIRSIZE);
                            CIRSIZE.KEY.INDUS = WS_INDUS;
                            T000_READ_CITSIZE_BY_INDUS();
                            if (pgmRtn) return;
                            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                                CEP.TRC(SCCGWA, "CITSIZE NOT FOUND");
                                Z_RET();
                                if (pgmRtn) return;
                            }
                        }
                    }
                }
            }
        }
    }
    public void B020_COMP_SIZE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_INDUS);
        IBS.init(SCCGWA, CIRSIZE);
        CIRSIZE.KEY.INDUS = WS_INDUS;
        CIRSIZE.KEY.COMP_SIZE = '1';
        T000_READ_CITSIZE();
        if (pgmRtn) return;
        WS_CHK_SIZE = 'N';
        R000_CHECK_SIZE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CHK_SIZE);
        if (WS_CHK_SIZE == 'Y') {
            CICUSIZE.DATA.COMP_SIZE = '1';
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRSIZE);
        CIRSIZE.KEY.INDUS = WS_INDUS;
        CIRSIZE.KEY.COMP_SIZE = '2';
        T000_READ_CITSIZE();
        if (pgmRtn) return;
        WS_CHK_SIZE = 'N';
        R000_CHECK_SIZE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CHK_SIZE);
        if (WS_CHK_SIZE == 'Y') {
            CICUSIZE.DATA.COMP_SIZE = '2';
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRSIZE);
        CIRSIZE.KEY.INDUS = WS_INDUS;
        CIRSIZE.KEY.COMP_SIZE = '3';
        T000_READ_CITSIZE();
        if (pgmRtn) return;
        WS_CHK_SIZE = 'N';
        R000_CHECK_SIZE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_CHK_SIZE);
        if (WS_CHK_SIZE == 'Y') {
            CICUSIZE.DATA.COMP_SIZE = '3';
        } else {
            CICUSIZE.DATA.COMP_SIZE = '4';
        }
    }
    public void R000_CHECK_SIZE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICUSIZE.DATA.OPER_INC);
        CEP.TRC(SCCGWA, CICUSIZE.DATA.TOTAL_ASS);
        CEP.TRC(SCCGWA, CICUSIZE.DATA.EMP_NUM);
        CEP.TRC(SCCGWA, CIRSIZE.OPER_INC);
        CEP.TRC(SCCGWA, CIRSIZE.TOTAL_ASS);
        CEP.TRC(SCCGWA, CIRSIZE.EMP_NUM);
        CEP.TRC(SCCGWA, CIRSIZE.COUNT_RULE);
        if (CIRSIZE.COUNT_RULE == '0') {
            R000_0_CHECK_SIZE();
            if (pgmRtn) return;
        } else if (CIRSIZE.COUNT_RULE == '1') {
            R000_1_CHECK_SIZE();
            if (pgmRtn) return;
        } else if (CIRSIZE.COUNT_RULE == '2') {
            R000_2_CHECK_SIZE();
            if (pgmRtn) return;
        } else {
        }
        CEP.TRC(SCCGWA, WS_CHK_SIZE);
    }
    public void R000_0_CHECK_SIZE() throws IOException,SQLException,Exception {
        if (CIRSIZE.OPER_INC != 0) {
            if (CICUSIZE.DATA.OPER_INC >= CIRSIZE.OPER_INC) {
                WS_CHK_SIZE = 'Y';
            }
        } else if (CIRSIZE.TOTAL_ASS != 0) {
            if (CICUSIZE.DATA.TOTAL_ASS >= CIRSIZE.TOTAL_ASS) {
                WS_CHK_SIZE = 'Y';
            }
        } else if (CIRSIZE.EMP_NUM != 0) {
            if (CICUSIZE.DATA.EMP_NUM >= CIRSIZE.EMP_NUM) {
                WS_CHK_SIZE = 'Y';
            }
        } else {
        }
    }
    public void R000_1_CHECK_SIZE() throws IOException,SQLException,Exception {
        if ((CIRSIZE.OPER_INC == 0 
            || CICUSIZE.DATA.OPER_INC >= CIRSIZE.OPER_INC) 
            && (CIRSIZE.TOTAL_ASS == 0 
            || CICUSIZE.DATA.TOTAL_ASS >= CIRSIZE.TOTAL_ASS) 
            && (CIRSIZE.EMP_NUM == 0 
            || CICUSIZE.DATA.EMP_NUM >= CIRSIZE.EMP_NUM)) {
            WS_CHK_SIZE = 'Y';
        }
    }
    public void R000_2_CHECK_SIZE() throws IOException,SQLException,Exception {
        if ((CIRSIZE.OPER_INC != 0 
            && CICUSIZE.DATA.OPER_INC >= CIRSIZE.OPER_INC) 
            || (CIRSIZE.TOTAL_ASS != 0 
            && CICUSIZE.DATA.TOTAL_ASS >= CIRSIZE.TOTAL_ASS) 
            || (CIRSIZE.EMP_NUM != 0 
            && CICUSIZE.DATA.EMP_NUM >= CIRSIZE.EMP_NUM)) {
            WS_CHK_SIZE = 'Y';
        }
    }
    public void T000_READ_CITSIZE() throws IOException,SQLException,Exception {
        CITSIZE_RD = new DBParm();
        CITSIZE_RD.TableName = "CITSIZE";
        IBS.READ(SCCGWA, CIRSIZE, CITSIZE_RD);
    }
    public void T000_READ_CITSIZE_BY_INDUS() throws IOException,SQLException,Exception {
        CITSIZE_RD = new DBParm();
        CITSIZE_RD.TableName = "CITSIZE";
        CITSIZE_RD.eqWhere = "INDUS";
        CITSIZE_RD.fst = true;
        IBS.READ(SCCGWA, CIRSIZE, CITSIZE_RD);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
