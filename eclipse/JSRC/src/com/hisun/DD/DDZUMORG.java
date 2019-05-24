package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class DDZUMORG {
    String JIBS_tmp_str[] = new String[10];
    DBParm DDTMST_RD;
    DBParm DDTCCY_RD;
    DBParm DDTMSTR_RD;
    DBParm DDTINTB_RD;
    boolean pgmRtn = false;
    int WS_OWNER_BR = 0;
    char WS_MST_REC_FLG = ' ';
    char WS_CCY_REC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    DDRMSTR DDRMSTR = new DDRMSTR();
    DDRINTB DDRINTB = new DDRINTB();
    CICCUST CICCUST = new CICCUST();
    CICSACR CICSACR = new CICSACR();
    CICSACAC CICSACAC = new CICSACAC();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    DDVMRAT DDVMRAT = new DDVMRAT();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    DDCUMORG DDCUMORG;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCUMORG DDCUMORG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCUMORG = DDCUMORG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZUMORG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (DDCUMORG.MIG_TYP == '1') {
            CEP.TRC(SCCGWA, DDCUMORG.VS_AC_FLG);
            if (DDCUMORG.VS_AC_FLG != 'Y') {
                B020_GET_CUS_AC_INFO();
                if (pgmRtn) return;
                if (WS_MST_REC_FLG == 'Y') {
                    B040_MIG_CUS_AC();
                    if (pgmRtn) return;
                }
            }
            if (WS_MST_REC_FLG == 'Y' 
                || DDCUMORG.VS_AC_FLG == 'Y') {
                B060_CI_UPD_CUS_AC();
                if (pgmRtn) return;
            }
        } else if (DDCUMORG.MIG_TYP == '2') {
            B030_GET_ACO_AC_INFO();
            if (pgmRtn) return;
            if (WS_CCY_REC_FLG == 'Y') {
                B050_MIG_ACO_AC();
                if (pgmRtn) return;
                B070_CI_UPD_ACO_AC();
                if (pgmRtn) return;
                if (BPCPQORG.BBR != WS_OWNER_BR) {
                    B080_GEN_VCH_PROC();
                    if (pgmRtn) return;
                }
            }
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID MIG TYPE(" + DDCUMORG.MIG_TYP + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCUMORG.MIG_TYP);
        CEP.TRC(SCCGWA, DDCUMORG.DD_AC);
        CEP.TRC(SCCGWA, DDCUMORG.BR_OLD);
        CEP.TRC(SCCGWA, DDCUMORG.BR_NEW);
        if (DDCUMORG.MIG_TYP == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MIG_TYP_M_INPUT);
        }
        if (DDCUMORG.DD_AC.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT);
        }
        if (DDCUMORG.BR_OLD == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_OLD_BR_M_INPUT);
        }
        if (DDCUMORG.BR_NEW == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_NEW_BR_M_INPUT);
        }
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = DDCUMORG.BR_NEW;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
    }
    public void B020_GET_CUS_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMST);
        DDRMST.KEY.CUS_AC = DDCUMORG.DD_AC;
        DDRMST.OWNER_BRDP = DDCUMORG.BR_OLD;
        CEP.TRC(SCCGWA, DDRMST.KEY.CUS_AC);
        T000_READ_DDTMST_UPD();
        if (pgmRtn) return;
    }
    public void B030_GET_ACO_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDCUMORG.DD_AC;
        DDRCCY.OWNER_BRDP = DDCUMORG.BR_OLD;
        CEP.TRC(SCCGWA, DDRCCY.KEY.AC);
        T000_READ_DDTCCY_UPD();
        if (pgmRtn) return;
        WS_OWNER_BR = DDRCCY.OWNER_BR;
        CEP.TRC(SCCGWA, WS_OWNER_BR);
    }
    public void B040_MIG_CUS_AC() throws IOException,SQLException,Exception {
        DDRMST.OPEN_DP = DDCUMORG.BR_NEW;
        DDRMST.OWNER_BRDP = DDCUMORG.BR_NEW;
        DDRMST.OWNER_BR = BPCPQORG.BBR;
        DDRMST.OWNER_BK = BPCPQORG.BRANCH_BR;
        DDRMST.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMST.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTMST();
        if (pgmRtn) return;
    }
    public void B050_MIG_ACO_AC() throws IOException,SQLException,Exception {
        DDRCCY.OPEN_DP = DDCUMORG.BR_NEW;
        DDRCCY.OWNER_BRDP = DDCUMORG.BR_NEW;
        DDRCCY.OWNER_BR = BPCPQORG.BBR;
        DDRCCY.OWNER_BK = BPCPQORG.BRANCH_BR;
        DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_DDTCCY();
        if (pgmRtn) return;
    }
    public void B060_CI_UPD_CUS_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'M';
        CICSACR.DATA.AGR_NO = DDCUMORG.DD_AC;
        CEP.TRC(SCCGWA, CICSACAC.DATA.AGR_NO);
        CICSACR.DATA.ENTY_TYP = '1';
        CICSACR.DATA.OPN_BR = DDCUMORG.BR_NEW;
        CICSACR.DATA.OWNER_BK = BPCPQORG.BRANCH_BR;
        S000_CALL_CIZSACR();
        if (pgmRtn) return;
    }
    public void B070_CI_UPD_ACO_AC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'M';
        CICSACAC.DATA.ACAC_NO = DDCUMORG.DD_AC;
        CEP.TRC(SCCGWA, CICSACAC.DATA.ACAC_NO);
        CICSACAC.DATA.OPN_BR = DDCUMORG.BR_NEW;
        CICSACAC.DATA.OWNER_BK = BPCPQORG.BRANCH_BR;
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void B080_GEN_VCH_PROC() throws IOException,SQLException,Exception {
        R000_INQ_CI_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NO);
        R000_INQ_PRD_INFO();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIQPRD.OUTPUT_DATA.PRDT_MODEL);
        R000_INQ_MSTR_INTB_INFO();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = DDCIQPRD.OUTPUT_DATA.PRDT_MODEL;
        BPCPOEWA.DATA.PROD_CODE = DDRCCY.PROD_CODE;
        BPCPOEWA.DATA.EVENT_CODE = "BC";
        BPCPOEWA.DATA.BR_OLD = DDCUMORG.BR_OLD;
        BPCPOEWA.DATA.BR_NEW = DDCUMORG.BR_NEW;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.AC_NO = DDRCCY.KEY.AC;
        BPCPOEWA.DATA.CI_NO = CICCUST.O_DATA.O_CI_NO;
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AC_NO);
        BPCPOEWA.DATA.CCY_INFO[01-1].CCY = DDRCCY.CCY;
        if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("4")
            || DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("5")
            || DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("6")) {
            BPCPOEWA.DATA.AMT_INFO[08-1].AMT = DDRCCY.CURR_BAL;
            if (DDRINTB.DEP_ACCU_INT > 0) {
                BPCPOEWA.DATA.AMT_INFO[09-1].AMT = DDRINTB.DEP_ACCU_INT;
            } else {
                BPCPOEWA.DATA.AMT_INFO[15-1].AMT = DDRINTB.DEP_ACCU_INT * -1;
            }
        } else if (DDRMSTR.KEY.ADP_TYPE.equalsIgnoreCase("3")) {
            BPCPOEWA.DATA.AMT_INFO[30-1].AMT = DDRCCY.CURR_BAL;
            if (DDRINTB.DEP_SPC_INT > 0) {
                BPCPOEWA.DATA.AMT_INFO[31-1].AMT = DDRINTB.DEP_SPC_INT;
            } else {
                BPCPOEWA.DATA.AMT_INFO[33-1].AMT = DDRINTB.DEP_SPC_INT * -1;
            }
        } else {
            if (DDRCCY.CURR_BAL > 0) {
                BPCPOEWA.DATA.AMT_INFO[01-1].AMT = DDRCCY.CURR_BAL;
            } else {
                BPCPOEWA.DATA.AMT_INFO[21-1].AMT = DDRCCY.CURR_BAL * -1;
            }
            if (DDRINTB.DEP_ACCU_INT > 0) {
                BPCPOEWA.DATA.AMT_INFO[02-1].AMT = DDRINTB.DEP_ACCU_INT;
            } else {
                BPCPOEWA.DATA.AMT_INFO[13-1].AMT = DDRINTB.DEP_ACCU_INT * -1;
            }
        }
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[01-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[08-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[30-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[02-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[13-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[09-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[15-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[31-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[33-1].AMT);
        CEP.TRC(SCCGWA, BPCPOEWA.DATA.AMT_INFO[21-1].AMT);
        if (BPCPOEWA.DATA.AMT_INFO[01-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[08-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[30-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[02-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[13-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[09-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[15-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[31-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[33-1].AMT != 0 
            || BPCPOEWA.DATA.AMT_INFO[21-1].AMT != 0) {
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
        }
    }
    public void R000_INQ_CI_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.FUNC = 'A';
        CICCUST.DATA.AGR_NO = DDRCCY.CUS_AC;
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
    }
    public void R000_INQ_PRD_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIQPRD);
        DDCIQPRD.INPUT_DATA.PROD_CODE = DDRCCY.PROD_CODE;
        CEP.TRC(SCCGWA, DDCIQPRD.INPUT_DATA.PROD_CODE);
        DDCIQPRD.INPUT_DATA.CCY = DDRCCY.CCY;
        DDCIQPRD.DDVMPRD_PTR = DDVMPRD;
        DDCIQPRD.DDVMRAT_PTR = DDVMRAT;
        S000_CALL_DDZIQPRD();
        if (pgmRtn) return;
    }
    public void R000_INQ_MSTR_INTB_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRMSTR);
        DDRMSTR.KEY.AC = DDRCCY.KEY.AC;
        DDRMSTR.KEY.ADP_STRDATE = SCCGWA.COMM_AREA.AC_DATE;
        DDRMSTR.ADP_STS = 'O';
        T000_READ_DDTMSTR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRMSTR.KEY.ADP_TYPE);
        IBS.init(SCCGWA, DDRINTB);
        DDRINTB.KEY.AC = DDRCCY.KEY.AC;
        DDRINTB.KEY.TYPE = 'D';
        T000_READ_DDTINTB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDRINTB.DEP_ACCU_INT);
        CEP.TRC(SCCGWA, DDRINTB.DEP_SPC_INT);
    }
    public void S000_CALL_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUMORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZSACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACAC", CICSACAC);
        if (CICSACAC.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACAC.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUMORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUMORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUMORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_DDZIQPRD() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-I-INQ-DDPRD", DDCIQPRD);
        if (DDCIQPRD.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, DDCIQPRD.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUMORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], DDCUMORG.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_DDTMST_UPD() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        DDTMST_RD.eqWhere = "CUS_AC, OWNER_BRDP";
        DDTMST_RD.upd = true;
        IBS.READ(SCCGWA, DDRMST, DDTMST_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_MST_REC_FLG = 'Y';
        }
    }
    public void T000_REWRITE_DDTMST() throws IOException,SQLException,Exception {
        DDTMST_RD = new DBParm();
        DDTMST_RD.TableName = "DDTMST";
        IBS.REWRITE(SCCGWA, DDRMST, DDTMST_RD);
    }
    public void T000_READ_DDTCCY_UPD() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.eqWhere = "AC, OWNER_BRDP";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CCY_REC_FLG = 'Y';
        }
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_READ_DDTMSTR() throws IOException,SQLException,Exception {
        DDTMSTR_RD = new DBParm();
        DDTMSTR_RD.TableName = "DDTMSTR";
        DDTMSTR_RD.eqWhere = "AC, ADP_STRDATE, ADP_STS";
        IBS.READ(SCCGWA, DDRMSTR, DDTMSTR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTINTB() throws IOException,SQLException,Exception {
        DDTINTB_RD = new DBParm();
        DDTINTB_RD.TableName = "DDTINTB";
        IBS.READ(SCCGWA, DDRINTB, DDTINTB_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
