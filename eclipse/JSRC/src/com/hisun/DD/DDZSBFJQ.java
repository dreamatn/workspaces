package com.hisun.DD;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSBFJQ {
    DBParm DDTRSAC_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_FMT_CODE = "DD533";
    short WS_CNT = 0;
    int WS_BASE_DT = 0;
    int WS_AC_DATE = 0;
    char WS_DDTRSAC_FLG = ' ';
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    DDCSCINM DDCSCINM = new DDCSCINM();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCFCSTS BPCFCSTS = new BPCFCSTS();
    BPCPGDIN BPCPGDIN = new BPCPGDIN();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCIQPRD DDCIQPRD = new DDCIQPRD();
    DDVMPRD DDVMPRD = new DDVMPRD();
    CICACCU CICACCU = new CICACCU();
    DDCSRATE DDCSRATE = new DDCSRATE();
    CICQACAC CICQACAC = new CICQACAC();
    CICQACRI CICQACRI = new CICQACRI();
    DDCF5233 DDCF5233 = new DDCF5233();
    CICSACR CICSACR = new CICSACR();
    DDRRSAC DDRRSAC = new DDRRSAC();
    DDRMST DDRMST = new DDRMST();
    DDRCCY DDRCCY = new DDRCCY();
    SCCGWA SCCGWA;
    DDCSBFJQ DDCSBFJQ;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPQBNK_DATA_INFO BPCRBANK;
    public void MP(SCCGWA SCCGWA, DDCSBFJQ DDCSBFJQ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSBFJQ = DDCSBFJQ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSBFJQ return!");
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
        B020_READ_DDTRSAC_PROC();
        if (pgmRtn) return;
        B030_PROCESS_OUTPUT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSBFJQ.CI_NO);
        CEP.TRC(SCCGWA, DDCSBFJQ.AC_TYPE);
        CEP.TRC(SCCGWA, DDCSBFJQ.OTH_AC);
        CEP.TRC(SCCGWA, DDCSBFJQ.TYPE);
        CEP.TRC(SCCGWA, DDCSBFJQ.CTL_TYPE);
        if (DDCSBFJQ.FUNC == ' ') {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MUST_INPUT);
        }
        if (DDCSBFJQ.FUNC == 'B' 
            && DDCSBFJQ.CI_NO.trim().length() == 0) {
            CEP.ERR(SCCGWA, DDCMSG_ERROR_MSG.DD_MUST_INPUT);
        }
    }
    public void B020_READ_DDTRSAC_PROC() throws IOException,SQLException,Exception {
        if (DDCSBFJQ.FUNC == 'B') {
            T000_READ_DDTRSAC();
            if (pgmRtn) return;
        } else if (DDCSBFJQ.FUNC == 'Q') {
            T000_READ_DDTRSAC_1();
            if (pgmRtn) return;
        } else if (DDCSBFJQ.FUNC == 'H') {
            T000_READ_DDTRSAC_2();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSBFJQ.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B030_PROCESS_OUTPUT() throws IOException,SQLException,Exception {
        DDCSBFJQ.S_LMT = DDRRSAC.SINGAL_LMT;
        DDCSBFJQ.DAMT_LMT = DDRRSAC.DAY_AMT_LMT;
        DDCSBFJQ.DCNT_LMT = DDRRSAC.DAY_CNT_LMT;
        DDCSBFJQ.MAMT_LMT = DDRRSAC.MON_AMT_LMT;
        DDCSBFJQ.MCNT_LMT = DDRRSAC.MON_CNT_LMT;
        DDCSBFJQ.BASE_LMT = DDRRSAC.BASE_AMT;
        DDCSBFJQ.AC_TYPE = DDRRSAC.KEY.AC_TYPE;
        IBS.init(SCCGWA, DDCF5233);
        DDCF5233.TYP = DDRRSAC.KEY.TYPE;
        DDCF5233.CTL_TYPE = DDRRSAC.KEY.CTL_TYPE;
        DDCF5233.AC_TYPE = DDRRSAC.KEY.AC_TYPE;
        DDCF5233.BANK_NO = DDRRSAC.KEY.PAY_BANK_NO;
        DDCF5233.CI_NO = DDRRSAC.CI_NO;
        DDCF5233.OTH_AC = DDRRSAC.KEY.OTH_AC;
        DDCF5233.AC_KND = DDRRSAC.AC_KND;
        DDCF5233.S_LMT = DDRRSAC.SINGAL_LMT;
        WS_BASE_DT = DDRRSAC.BASE_DATE;
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        if (WS_BASE_DT != WS_AC_DATE 
            && DDRRSAC.KEY.CTL_TYPE == '3') {
            DDCF5233.DAMT_LMT = 0;
        } else {
            DDCF5233.DAMT_LMT = DDRRSAC.DAY_AMT_LMT;
        }
        DDCF5233.DCNT_LMT = DDRRSAC.DAY_CNT_LMT;
        DDCF5233.MAMT_LMT = DDRRSAC.MON_AMT_LMT;
        DDCF5233.MCNT_LMT = DDRRSAC.MON_CNT_LMT;
        DDCF5233.BASE_LMT = DDRRSAC.BASE_AMT;
        if (WS_BASE_DT != WS_AC_DATE) {
            DDCF5233.DAY_TOT_AMT = 0;
            DDCF5233.DAY_TOT_CNT = 0;
            DDCF5233.MON_TOT_CNT = 0;
            DDCF5233.MON_TOT_AMT = 0;
        } else {
            DDCF5233.DAY_TOT_AMT = DDRRSAC.DAY_TOT_AMT;
            DDCF5233.DAY_TOT_CNT = DDRRSAC.DAY_TOT_CNT;
            DDCF5233.MON_TOT_CNT = DDRRSAC.MON_TOT_CNT;
            DDCF5233.MON_TOT_AMT = DDRRSAC.MON_TOT_AMT;
        }
        CEP.TRC(SCCGWA, DDCF5233.TYP);
        CEP.TRC(SCCGWA, DDCF5233.CTL_TYPE);
        CEP.TRC(SCCGWA, DDCF5233.AC_TYPE);
        CEP.TRC(SCCGWA, DDCF5233.BANK_NO);
        CEP.TRC(SCCGWA, DDCF5233.CI_NO);
        CEP.TRC(SCCGWA, DDCF5233.OTH_AC);
        CEP.TRC(SCCGWA, DDCF5233.AC_KND);
        CEP.TRC(SCCGWA, DDCF5233.S_LMT);
        CEP.TRC(SCCGWA, DDCF5233.DAMT_LMT);
        CEP.TRC(SCCGWA, DDCF5233.DCNT_LMT);
        CEP.TRC(SCCGWA, DDCF5233.MAMT_LMT);
        CEP.TRC(SCCGWA, DDCF5233.MCNT_LMT);
        CEP.TRC(SCCGWA, DDCF5233.BASE_LMT);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_FMT_CODE;
        SCCFMT.DATA_PTR = DDCF5233;
        SCCFMT.DATA_LEN = 211;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_READ_DDTRSAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.CI_NO = DDCSBFJQ.CI_NO;
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        DDTRSAC_RD.where = "CI_NO = :DDRRSAC.CI_NO "
            + "AND TYPE = '0' "
            + "AND CTL_TYPE = '1' "
            + "AND AC_TYPE = '1'";
        IBS.READ(SCCGWA, DDRRSAC, this, DDTRSAC_RD);
    }
    public void T000_READ_DDTRSAC_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.CI_NO = DDCSBFJQ.CI_NO;
        DDRRSAC.KEY.OTH_AC = DDCSBFJQ.OTH_AC;
        DDRRSAC.KEY.AC_TYPE = DDCSBFJQ.AC_TYPE;
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        DDTRSAC_RD.where = "CI_NO = :DDRRSAC.CI_NO "
            + "AND OTH_AC = :DDRRSAC.KEY.OTH_AC "
            + "AND TYPE = '2' "
            + "AND CTL_TYPE = '2' "
            + "AND AC_TYPE = '1' "
            + "AND AC_TYPE = :DDRRSAC.KEY.AC_TYPE";
        IBS.READ(SCCGWA, DDRRSAC, this, DDTRSAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_DDTRSAC_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRRSAC);
        DDRRSAC.CI_NO = DDCSBFJQ.CI_NO;
        DDRRSAC.KEY.OTH_AC = DDCSBFJQ.OTH_AC;
        DDRRSAC.KEY.AC_TYPE = DDCSBFJQ.AC_TYPE;
        DDTRSAC_RD = new DBParm();
        DDTRSAC_RD.TableName = "DDTRSAC";
        DDTRSAC_RD.where = "CI_NO = :DDRRSAC.CI_NO "
            + "AND OTH_AC = :DDRRSAC.KEY.OTH_AC "
            + "AND TYPE = '2' "
            + "AND CTL_TYPE = '3' "
            + "AND AC_TYPE = '1' "
            + "AND AC_TYPE = :DDRRSAC.KEY.AC_TYPE";
        IBS.READ(SCCGWA, DDRRSAC, this, DDTRSAC_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
