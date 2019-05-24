package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class CIZQACRL {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    DBParm CITACR_RD;
    DBParm CITACRL_RD;
    brParm CITACRL_BR = new brParm();
    boolean pgmRtn = false;
    short WS_I = 0;
    char WS_DUPREC_FLG = ' ';
    String WS_AC_NO = " ";
    String WS_REL_AC_NO = " ";
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CICOACLB CICOACLB = new CICOACLB();
    CICQCHDC CICQCHDC = new CICQCHDC();
    CIRACR CIRACR = new CIRACR();
    CIRACRL CIRACRL = new CIRACRL();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICQACRL CICQACRL;
    public void MP(SCCGWA SCCGWA, CICQACRL CICQACRL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICQACRL = CICQACRL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZQACRL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICOACLB);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (CICQACRL.FUNC == 'I') {
            B030_INQUIRE_ACRL_INF();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC CODE INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        if (CICQACRL.DATA.AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICQACRL.DATA.AC_NO;
            T000_READ_CITACR_EXIST();
            if (pgmRtn) return;
            if (CIRACR.STSW == null) CIRACR.STSW = "";
            JIBS_tmp_int = CIRACR.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
            if (CIRACR.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, CICQCHDC);
                CICQCHDC.DATA.O_AGR_NO = CIRACR.KEY.AGR_NO;
                CICQCHDC.DATA.O_ENTY_TYP = CIRACR.ENTY_TYP;
                CICQCHDC.FUNC = 'W';
                S000_CALL_CIZQCHDC();
                if (pgmRtn) return;
                WS_AC_NO = CICQCHDC.DATA.N_AGR_NO;
            } else {
                WS_AC_NO = CICQACRL.DATA.AC_NO;
            }
        }
        CEP.TRC(SCCGWA, CICQACRL.DATA.REL_AC_NO);
        if (CICQACRL.DATA.REL_AC_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.KEY.AGR_NO = CICQACRL.DATA.REL_AC_NO;
            T000_READ_CITACR_EXIST();
            if (pgmRtn) return;
            if (CIRACR.STSW == null) CIRACR.STSW = "";
            JIBS_tmp_int = CIRACR.STSW.length();
            for (int i=0;i<8-JIBS_tmp_int;i++) CIRACR.STSW += " ";
            if (CIRACR.STSW.substring(8 - 1, 8 + 1 - 1).equalsIgnoreCase("1")) {
                IBS.init(SCCGWA, CICQCHDC);
                CICQCHDC.DATA.O_AGR_NO = CIRACR.KEY.AGR_NO;
                CICQCHDC.DATA.O_ENTY_TYP = CIRACR.ENTY_TYP;
                CICQCHDC.FUNC = 'W';
                S000_CALL_CIZQCHDC();
                if (pgmRtn) return;
                WS_REL_AC_NO = CICQCHDC.DATA.N_AGR_NO;
            } else {
                WS_REL_AC_NO = CICQACRL.DATA.REL_AC_NO;
            }
        }
    }
    public void B020_BROWSE_ACRL_INF() throws IOException,SQLException,Exception {
        if (CICQACRL.FUNC == '1') {
            B020_BROWSE_ACRL_BY_AC();
            if (pgmRtn) return;
        } else if (CICQACRL.FUNC == '2') {
            B020_BROWSE_ACRL_BY_RAC();
            if (pgmRtn) return;
        } else if (CICQACRL.FUNC == '3') {
            B020_BROWSE_ACRL_BY_AC_R();
            if (pgmRtn) return;
        } else if (CICQACRL.FUNC == '4') {
            B020_BROWSE_ACRL_BY_RAC_R();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "FUNC CODE INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        T000_READNEXT_CITACRL();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_NOTFND, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        for (WS_I = 1; SCCGWA.COMM_AREA.DBIO_FLG != '1' 
            && WS_I <= 300; WS_I += 1) {
            R000_TRANS_DATA_TO_OUTPUT_B();
            if (pgmRtn) return;
            T000_READNEXT_CITACRL();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACRL();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_ACRL_BY_AC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        if (CICQACRL.DATA.AC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACR OF INPUT INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_NO = WS_AC_NO;
        T000_STARTBR_CITACRL_AC();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_ACRL_BY_RAC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRL.DATA.REL_AC_NO);
        if (CICQACRL.DATA.REL_AC_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACR OF INPUT INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.REL_AC_NO = WS_REL_AC_NO;
        T000_STARTBR_CITACRL_RAC();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_ACRL_BY_AC_R() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_REL);
        if (CICQACRL.DATA.AC_NO.trim().length() == 0 
            || CICQACRL.DATA.AC_REL.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACR OF INPUT INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_NO = WS_AC_NO;
        CIRACRL.KEY.AC_REL = CICQACRL.DATA.AC_REL;
        T000_STARTBR_CITACRL_AC_R();
        if (pgmRtn) return;
    }
    public void B020_BROWSE_ACRL_BY_RAC_R() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRL.DATA.REL_AC_NO);
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_REL);
        if (CICQACRL.DATA.REL_AC_NO.trim().length() == 0 
            || CICQACRL.DATA.AC_REL.trim().length() == 0) {
            CEP.TRC(SCCGWA, "LACR OF INPUT INFORMATION");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.REL_AC_NO = WS_REL_AC_NO;
        CIRACRL.KEY.AC_REL = CICQACRL.DATA.AC_REL;
        T000_STARTBR_CITACRL_RAC_R();
        if (pgmRtn) return;
    }
    public void B030_INQUIRE_ACRL_INF() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_NO);
        CEP.TRC(SCCGWA, CICQACRL.DATA.AC_REL);
        CEP.TRC(SCCGWA, CICQACRL.DATA.REL_AC_NO);
        if (CICQACRL.DATA.AC_NO.trim().length() > 0 
                && CICQACRL.DATA.REL_AC_NO.trim().length() > 0) {
            B030_INQUIRE_BY_ALL_PARTS();
            if (pgmRtn) return;
        } else if (CICQACRL.DATA.AC_NO.trim().length() > 0) {
            B030_INQUIRE_BY_AC();
            if (pgmRtn) return;
        } else if (CICQACRL.DATA.REL_AC_NO.trim().length() > 0) {
            B030_INQUIRE_BY_REL_AC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "LACK OF INFORMATION FOR INQUIRE");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_MUST_INPUT, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        R000_TRANS_DATA_TO_OUTPUT_I();
        if (pgmRtn) return;
    }
    public void B030_INQUIRE_BY_ALL_PARTS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACRL);
        CIRACRL.KEY.AC_NO = WS_AC_NO;
        CIRACRL.KEY.REL_AC_NO = WS_REL_AC_NO;
        T000_READ_CITACRL();
        if (pgmRtn) return;
    }
    public void B030_INQUIRE_BY_AC() throws IOException,SQLException,Exception {
        if (CICQACRL.DATA.AC_REL.trim().length() == 0) {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_NO = WS_AC_NO;
            T000_READ_CITACRL_BY_AC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.AC_NO = WS_AC_NO;
            CIRACRL.KEY.AC_REL = CICQACRL.DATA.AC_REL;
            if (CICQACRL.DATA.AC_REL.equalsIgnoreCase("01") 
                || CICQACRL.DATA.AC_REL.equalsIgnoreCase("03") 
                || CICQACRL.DATA.AC_REL.equalsIgnoreCase("05") 
                || CICQACRL.DATA.AC_REL.equalsIgnoreCase("06") 
                || CICQACRL.DATA.AC_REL.equalsIgnoreCase("07") 
                || CICQACRL.DATA.AC_REL.equalsIgnoreCase("09") 
                || CICQACRL.DATA.AC_REL.equalsIgnoreCase("10") 
                || CICQACRL.DATA.AC_REL.equalsIgnoreCase("11") 
                || CICQACRL.DATA.AC_REL.equalsIgnoreCase("12") 
                || CICQACRL.DATA.AC_REL.equalsIgnoreCase("13")) {
                T000_READ_CITACRL_BY_AC_R2();
                if (pgmRtn) return;
            } else {
                T000_READ_CITACRL_BY_AC_R();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, CIRACRL.KEY.REL_AC_NO);
        WS_REL_AC_NO = CIRACRL.KEY.REL_AC_NO;
        CEP.TRC(SCCGWA, WS_REL_AC_NO);
    }
    public void B030_INQUIRE_BY_REL_AC() throws IOException,SQLException,Exception {
        if (CICQACRL.DATA.AC_REL.trim().length() == 0) {
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.REL_AC_NO = WS_REL_AC_NO;
            T000_READ_CITACRL_BY_REL_AC();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "AAA");
            IBS.init(SCCGWA, CIRACRL);
            CIRACRL.KEY.REL_AC_NO = WS_REL_AC_NO;
            CIRACRL.KEY.AC_REL = CICQACRL.DATA.AC_REL;
            T000_READ_CITACRL_BY_REL_AC_R();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CIRACRL.KEY.REL_AC_NO);
        WS_AC_NO = CIRACRL.KEY.AC_NO;
        CEP.TRC(SCCGWA, WS_AC_NO);
    }
    public void R000_TRANS_DATA_TO_OUTPUT_I() throws IOException,SQLException,Exception {
        CICQACRL.O_DATA.O_AC_NO = WS_AC_NO;
        CICQACRL.O_DATA.O_AC_REL = CIRACRL.KEY.AC_REL;
        CICQACRL.O_DATA.O_REL_AC_NO = WS_REL_AC_NO;
        CICQACRL.O_DATA.O_REL_STS = CIRACRL.KEY.REL_STS;
        CICQACRL.O_DATA.O_REL_CTL = CIRACRL.KEY.REL_CTL;
        CICQACRL.O_DATA.O_EFF_DT = CIRACRL.EFF_DT;
        CICQACRL.O_DATA.O_EXP_DT = CIRACRL.EXP_DT;
        CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
    }
    public void R000_TRANS_DATA_TO_OUTPUT_B() throws IOException,SQLException,Exception {
        CICOACLB.COUNT += 1;
        CICOACLB.DATA[WS_I-1].AC_NO = CIRACRL.KEY.AC_NO;
        CICOACLB.DATA[WS_I-1].AC_REL = CIRACRL.KEY.AC_REL;
        CICOACLB.DATA[WS_I-1].REL_AC_NO = CIRACRL.KEY.REL_AC_NO;
        CICOACLB.DATA[WS_I-1].REL_STS = CIRACRL.KEY.REL_STS;
        CICOACLB.DATA[WS_I-1].REL_CTL = CIRACRL.KEY.REL_CTL;
        CICOACLB.DATA[WS_I-1].EFF_DT = CIRACRL.EFF_DT;
        CICOACLB.DATA[WS_I-1].EXP_DT = CIRACRL.EXP_DT;
    }
    public void S000_CALL_CIZQCHDC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CHDC", CICQCHDC);
        if (CICQCHDC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICQCHDC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACR_EXIST() throws IOException,SQLException,Exception {
        CITACR_RD = new DBParm();
        CITACR_RD.TableName = "CITACR";
        IBS.READ(SCCGWA, CIRACR, CITACR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "ACR INF NOT FOUND");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACR_INF_NOTFND, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACR";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACRL() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO, REL_AC_NO";
        CITACRL_RD.where = "EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_NOTFND, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '4') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_DUPREC, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACRL_BY_AC() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO";
        CITACRL_RD.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE ) "
            + "AND SUBSTR ( REL_CTL , 1 , 1 ) = '1' "
            + "AND AC_REL IN ( '01' , '02' , '03' , '04' , '09' , '13' )";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_NOTFND, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '4') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_DUPREC, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACRL_BY_AC_R() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO , AC_REL";
        CITACRL_RD.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE ) "
            + "AND SUBSTR ( REL_CTL , 1 , 1 ) = '1'";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_NOTFND, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '4') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_DUPREC, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACRL_BY_AC_R2() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_NO , AC_REL";
        CITACRL_RD.where = "EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE";
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_NOTFND, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '4') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_DUPREC, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACRL_BY_REL_AC() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "REL_AC_NO";
        CITACRL_RD.where = "( EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE ) "
            + "AND AC_REL IN ( '01' , '02' , '03' , '04' , '09' , '13' )";
        CITACRL_RD.fst = true;
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_NOTFND, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '4') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_DUPREC, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_CITACRL_BY_REL_AC_R() throws IOException,SQLException,Exception {
        CITACRL_RD = new DBParm();
        CITACRL_RD.TableName = "CITACRL";
        CITACRL_RD.eqWhere = "AC_REL , REL_AC_NO";
        CITACRL_RD.where = "EXP_DT = 0 "
            + "OR EXP_DT > :SCCGWA.COMM_AREA.AC_DATE";
        CITACRL_RD.fst = true;
        IBS.READ(SCCGWA, CIRACRL, this, CITACRL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_NOTFND, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '4') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_ACRL_INF_DUPREC, CICQACRL.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "CITACRL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_CITACRL_AC() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "AC_NO";
        IBS.STARTBR(SCCGWA, CIRACRL, CITACRL_BR);
    }
    public void T000_STARTBR_CITACRL_RAC() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "REL_AC_NO";
        IBS.STARTBR(SCCGWA, CIRACRL, CITACRL_BR);
    }
    public void T000_STARTBR_CITACRL_AC_R() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "AC_NO, AC_REL";
        IBS.STARTBR(SCCGWA, CIRACRL, CITACRL_BR);
    }
    public void T000_STARTBR_CITACRL_RAC_R() throws IOException,SQLException,Exception {
        CITACRL_BR.rp = new DBParm();
        CITACRL_BR.rp.TableName = "CITACRL";
        CITACRL_BR.rp.eqWhere = "REL_AC_NO, AC_REL";
        IBS.STARTBR(SCCGWA, CIRACRL, CITACRL_BR);
    }
    public void T000_READNEXT_CITACRL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRACRL, this, CITACRL_BR);
    }
    public void T000_ENDBR_CITACRL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITACRL_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
